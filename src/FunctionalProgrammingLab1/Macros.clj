(ns FunctionalProgrammingLab1.Macros)

;Гомоиконность всё есть данные программу (код) рассматриваем как данные
(println (+ 4 3))
;(infix 1 + 3)                                               ;название макроса
(defmacro infix [a op b] (list op a b))                         ;листом возвращаем результат
(println (infix 1 + 3))
(println (infix 1 - 3))
(println (macroexpand-1 '(infix 1 + 3)))                    ; кавычка преостанавливает вычисление и возвращает макрос
(defmacro unless [test then else] (if test else then))      ;должен переворачивать if
(unless (> 2 3) (println "a") (println "b"))
(println (macroexpand-1 '(unless (> 2 3) (println "a") (println "b"))))

(defmacro unless [test then else] (list 'if test then else))
(unless (> 2 3) (println "a") (println "b"))
(println (macroexpand-1 '(unless (> 2 3) (println "a") (println "b"))))

(defmacro unless [test then else] `(if ~test ~then ~else))  ;квазицитирование
(unless (> 2 3) (println "a") (println "b"))
(println (macroexpand-1 '(unless (> 2 3) (println "a") (println "b"))))



