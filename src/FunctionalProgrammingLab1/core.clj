(ns FunctionalProgrammingLab1.core)

(defn foo
  "I don't do a whole lot."
  [x]
  (println x "Hello, World!"))
(println "GAS GAS GAS")
(println (first [1 2 3 4 5]))                               ; Берём первый элемент
(println (rest [1 2 3 4 5]))                                ; Удаляем первый элемент
(println (take 3 [1 2 3 4 5]))                              ; Берём несколько первых
(println (drop 2 [1 2 3 4 5]))                              ; Удалить несколько первых
(println (if (> 4 5) "Yes" "No"))

(let [v [4 5 2]
      size (count v)]
  (loop [i 0]
    (if (< i size)
      (do
        (println i (v i))
        (recur (inc i))                                     ;Вызывает луп ещё раз
        )
      )
    )
  )
(let [v [4 5 2]
      size (count v)]
  (loop [i 0]                                               ; Итерируем по i с нуля
    (when (< i size)
        (println i (v i))
        (recur (inc i))
      )
    )
  )

(println (for [x [1 2 3 4]] x))
(println (for [x [1 2 3 4]]  (inc x)))

(println (for [x [1 2 3] y [:a :b :c :d]]
  [x y]))

(defn nums1 [start end]                                      ; Создание списка из N чисел
  (if (>= start end)
    [end]
    (cons start (nums1 (inc start) end)))                   ;cons присоединяет
  )
(println (nums1 1 10))

(defn nums2 [start end]                                      ; Создание списка из N чисел и стак не переполняется
  (if (>= start end)
    [end]
    (cons start (lazy-seq (nums2 (inc start) end))))         ; lazy-seq откладывает вычисление до момента как оно понадобится, явный пример замыкания
  )
(println (nums2 1 10))

(defn nums3 [start]                                      ; Создание списка из N чисел и стак не переполняется
    (cons start (lazy-seq (nums3 (inc start)))))         ; lazy-seq откладывает вычисление до момента как оно понадобится, явный пример замыкания
(println (take 100 (nums3 1)))

(defn removo [val l]
  (when-not (empty? l)                                      ; пока не пуст
    (if (= val (first l))
    (removo val (rest l))                                   ;removo можно заменить recur, не падает с переполнением стека
    (cons (first l) (removo val (rest l)))
    )
    )
  )
(println (removo 3 [1 2 3 4 3 4 3 4]))