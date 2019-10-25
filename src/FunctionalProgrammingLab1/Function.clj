(ns FunctionalProgrammingLab1.Function)

(defn removo [val [head & tail]]                            ;отделяем хвост от головы
  (when head                                ; пока не пуст
    (if (val head)                                 ;removo можно заменить recur, не падает с переполнением стека
      (cons head (removo val tail))
      (removo val tail)
      )
    )
  )
;(println (removo pos? [1 - 2 2 -3 4 3 -4 3 4])) ;проверка на положительность
;(println (filter #(> % 3) [1 - 2 2 -3 4 3 -4 3 4]))
(println (map inc [1 2 3 4]))                               ; увеличиваем массив на 1
(println (reduce conj [] [1 2 3 4]))
(println (reduce conj () [1 2 3 4]))
(println (reductions conj () [1 2 3 4]))
(println (mapcat (fn [i] (range 0 i)) [1 2 3 4]))           ;вывод послед от 0 до каждого элемента
(println (apply concat (map (fn [i] (range 0 i)) [1 2 3 4])))
(println (reduce + (filter #(> % 2) (map count ["asdasda" "ad" "asdasdsd" "a" "s"]))))
(println (reduce + (filter #(> % 2) (map count ["asdasda" "ad" "asdasdsd" "a" "s"])))) ; берём длины строк и складываем только те, которые больше 2
(println (->> ["asdasda" "ad" "asdasdsd" "a" "s"] (map count) (filter #(> % 2)) (reduce +))) ; Макрос, меняет порядок действей на более воспринимаемый

(println (for [str ["asdas" "asdasd" "aa" "a" "asd"]                       ;присваевае переменные и задам условия,
               :let [l (count str)]                         ;объявляем локальные переменные
               :when (> l 2)]                               ;Само условие
           l))                                              ;выведет длины больше 2
(println (for [x [ 1 2 3] y [1 2 3]] [x y]))

(println (for [str ["asdas" "asdasd" "aa" "a" "asd"] ;присваевае переменные и задам условия,
               c str                                        ;Фактически, цикл по символам
               :let [l (count(filter #(= % c) str))]                         ;объявляем локальные переменные
               :when (> l 1)]                               ;Само условие
           [c str]))                                              ;выведет Все повторяющиеся

(println (for [str ["asdas" "asdasd" "aa" "a" "asd"] ;присваевае переменные и задам условия,
               c (distinct str)                                        ;Фактически, цикл по символам, но берём только различные
               :let [l (count(filter #(= % c) str))]                         ;объявляем локальные переменные
               :when (> l 1)]                               ;Само условие
           [c str l]))                                              ;выведет Все повторяющиеся
;(print (doc count))                                                  ; документация
(defn sum "calculates sum of two params" [a b] (+ a b))
;(doc sum) ;по идее выведется строчка "calculates sum of two params"
(defn sum "calculates sum of two params" {:private true :author "Alex" } [a b] (+ a b)) ;указание меты в виде автора и приватности
(println (meta (var sum)))
(defn sum "calculates sum of two params" {:private true :author "Alex"} [a b] {:pre [(number? a) (number? b) (pos? a) (pos? b)] :post [(number? %)]} (+ a b)) ; контрактное программирование, пре условие проверка входящих на число и больше нуля и проверка ответа на число
(println (sum 10 10))
(defn sum "calculates sum of two params" {:private true :author "Alex" } [^long a ^long b] (+ a b)) ;указание типов у a и b
(println (sum 1N 2N))                                                 ; типа преведение типов
(defn foo [^String str] (.indexOf str "abc"))
(println (foo "aaasbabc"))
(defn sum
  ([] (+))
  ([a] (+ a))
  ([a b] (+ a b))
  ([a b c] (+ a b c))
  )
(println (sum 10 4N 3))

(defn sum
  ([] (+))
  ([a] (+ a))
  ([a b] (+ a b))
  ([a b c & rest] (reduce + (+ a b c) rest))                ;reduce можно заменить на apply
  )
(println (sum 10 4N 3))

(defn sum
  ([& rest] (apply + rest))                ;reduce можно заменить на apply
  )
(println (sum 10 4N 3))
(def v [3 5 6 7])
(let [[a1 & rest] v] (println "al=" a1 "rest=" rest))       ; забираем первый элемент
(let [[a1 a2 & rest] v] (println "al=" a1 "a2=" a2 "rest=" rest)); забираем первые два элемента
(println (let [a v] a))
(let [[n1 n2 n3 [str1 str2]] [1 2 3 ["a" "b"]]]             ;деструктурирование
  (println n1 n2 n3 str1 str2)
  )
(let [[n1 n2 n3 [str1 str2] :as full] [1 2 3 ["a" "b"]]]             ;деструктурирование
  (println n1 n2 n3 str1 str2 full)
  )
(def john {:name "John" :surname "Smith"})
(println (let [p john] (:name p)))
(println (let [{name :name surname :surname} john] [name surname]));поиск по имени переменной в Джоне
(println (let [{name :name surname :surname aa :aa} john] [name surname aa]));поиск по имени переменной в Джоне
(println (let [{:keys [name surname]} john] [name surname])) ;поиск по имени переменной в Джоне с сахаром
(println (let [{:keys [name surname] :as j} john] [j])) ;поиск по имени переменной в Джоне с сахаром и целиком
(println (let [{:keys [name surname age] :or {age 1}} john] [name surname age])) ;поиск по имени переменной в Джоне с сахаром и целиком

(def john {:name "John" :surname "Smith" :adress {:city "Moscow" :street "Vadkovsky per"}})
(println (let [{:keys [name surname age] {:keys [city street]} :adress} john] [name surname age city street]))