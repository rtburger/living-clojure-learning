(ns lc_ch8.core)

(defn -main []
  (println "Living Clojure Chapter 8"))

(defmacro when
  "Evaluates test. If logical true, evaluates body in an implicit do."
  {:added "1.0"}
  [test & body]
  (list 'if test (cons 'do body)))

(comment (-main))