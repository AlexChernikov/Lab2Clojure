(ns FunctionalProgrammingLab1.lab1
  (:require [clojure.set :as set]))

; let в некотором смысле позволяет создавать временные аргументы,
; которые можно использовать в зоне видимости.
; (let [x 1] x)
;
; (let [x 1
;   y (+ x 3)] // Инициализация x = 1, y = 4
;     (+ x 2 y)) // Выведет 7
;(let [v [4 5 2]
;      size (count v)]
;  (loop [i 0]
;    (if (< i size)
;      (do
;        (println i (v i))
;        (recur (inc i))
;        )
;      )
;    )
;  )

;(defn randArr [n, max]
;  (take n (repeatedly #(rand-int max))))
;
;(defn f [arr n]
;  (let [size (count arr)]
;  (loop [i 0]
;    (if (< i size)
;      (do
;        (print i)
;      (loop [j i, n 0]
;        (if (< j size)
;          (do
;            (print (arr j))
;            (recur (inc j) (inc n)))
;          )
;        )
;        (println)
;        (recur (inc i))
;      )
;    )
;  )
;  )
;  )
;
;(f [3 3 3 3 3 3] 0)

(defn worker [arr n i item]
  (when-not (empty? arr)
    (if (= item (first arr))
      (if (>= i n)
        (recur (rest arr) n (inc i) (first arr))            ; worker заменим на recur он это оптимизирует в хвостовую рекурсию
        (cons (first arr) (lazy-seq (worker (rest arr) n (inc i) (first arr)))) ; recur не может использоваться в cons
        )
        (cons (first arr) (lazy-seq (worker (rest arr) n 1 (first arr))))
        )
    )
  )

(defn removo [arr n]
  (cons (first arr) (worker (rest arr) n 1 (first arr)))
  )

(println (take 10 (removo (repeat 1) 11)))