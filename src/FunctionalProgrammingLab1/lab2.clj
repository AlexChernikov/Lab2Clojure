(ns FunctionalProgrammingLab1.lab2
  (:require [clojure.core.async :refer [<! >!] :as a]))

(defn produser [vec]
   (let [c (a/chan)]
     (a/go (doseq [x vec]
          (>! c x))
           (a/close! c)
           )
     c))

(defn sort-by-mod [c n]
  (let [vec-vec [(a/chan 10)
                 (a/chan 10)
                 (a/chan 10)
                 (a/chan 10)
                 (a/chan 10)
                 (a/chan 10)
                 (a/chan 10)
                 (a/chan 10)
                 (a/chan 10)
                 (a/chan 10)]]
  (a/go-loop []
    (when-some [value (<! c)]
      (println "value " value)
      (println "mod = " (mod value n))
      (>! (vec-vec (mod value n)) value)
      (recur)
      )
    )
  vec-vec)
)

(defn consumer [c]
  (Thread/sleep 1000)
  (println c)
  (a/go-loop []
             (when-some [value (<! c)]
               (print value " ")
               (recur))
             )
  ;(Thread/sleep 1000)
  )

(def c (produser (range 0 20)))
(def VECVEC (sort-by-mod c 2))

(consumer (VECVEC 0))
(consumer (VECVEC 1))