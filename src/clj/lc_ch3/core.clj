(ns lc_ch3.core)

(defn -main []
  (println "Living Clojure Chapter 3"))

; State and Concurrency
; using Atoms for Independent Items
(def who-atom (atom :caterpillar))
who-atom
@who-atom
(reset! who-atom :chrysalis)
@who-atom
(def who-atom (atom :caterpillar))
@who-atom
(defn change [state]
  (case state
    :caterpillar :chrysalis
    :chrysalis :butterfly
    :butterfly))

(swap! who-atom change)
@who-atom
(def counter (atom 0))
@counter
(dotimes [_ 5] (swap! counter inc))
@counter
(def counter (atom 0))
@counter
(let [n 5]
          (future (dotimes [_ n] (swap! counter inc)))
          (future (dotimes [_ n] (swap! counter inc)))
          (future (dotimes [_ n] (swap! counter inc))))
@counter

(def counter (atom 0))
(defn inc-print [val]
  (println val)
  (inc val))

(swap! counter inc-print)

(def counter (atom 0))
(let [n 2]
  (future (dotimes [_ n] (swap! counter inc-print)))
  (future (dotimes [_ n] (swap! counter inc-print)))
  (future (dotimes [_ n] (swap! counter inc-print))))
@counter

; Using Refs for Coordinated Changes

(def alice-height (ref 3))
(def right-hand-bits (ref 10))

@alice-height
@right-hand-bits

(defn eat-from-right-hand []
  (dosync (when (pos? @right-hand-bits)
    (alter right-hand-bits dec)
    (alter alice-height #(+ % 24)))))

(defn eat-from-right-hand []
   (when (pos? @right-hand-bits)
            (alter right-hand-bits dec)
            (alter alice-height #(+ % 24))))

(eat-from-right-hand)

(dosync (eat-from-right-hand))
@right-hand-bits
@alice-height

(let [n 2]
  (future (dotimes [_ n] (eat-from-right-hand)))
  (future (dotimes [_ n] (eat-from-right-hand)))
  (future (dotimes [_ n] (eat-from-right-hand))))
@alice-height
@right-hand-bits

(defn eat-from-right-hand []
  (dosync (when (pos? @right-hand-bits)
            (commute right-hand-bits dec)
            (commute alice-height #(+ % 24)))))


(let [n 2]
  (future (dotimes [_ n] (eat-from-right-hand)))
  (future (dotimes [_ n] (eat-from-right-hand)))
  (future (dotimes [_ n] (eat-from-right-hand))))

@alice-height
@right-hand-bits

(def x (ref 1))
(def y (ref 1))

(defn new-values []
  (dosync
   (alter x inc)
   (ref-set y (+ 2 @x))))

(let [n 2]
  (future (dotimes [_ n] (new-values)))
  (future (dotimes [_ n] (new-values))))


; Using Agents to Manage Changes on Their Own
(def who-agent (agent :caterpillar))
@who-agent
(def who-agent (agent :caterpillar))
(defn change [state]
  (case state
    :caterpillar :chrysalis
    :chrysalis :butterfly
    :butterflye))
(send who-agent change)
@who-agent
(send-off who-agent change)
@who-agent
(def who-agent (agent :caterpillar))

(defn change [state]
  (case state
    :caterpillar :chrysalis
    :chrysalis :butterfly
    :butterfly))

(defn change-error [state]
  (throw (Exception. "Boom!")))

(send who-agent change-error)
@who-agent
(send-off who-agent change)
(agent-error who-agent)
(restart-agent who-agent :caterpillar)
(send who-agent change)
@who-agent
(set-error-mode! who-agent :continue)
(defn err-handler-fn [a ex]
  (println "error " ex " value is " @a))
(set-error-handler! who-agent err-handler-fn)
(def who-agent (agent :caterpillar))
(set-error-mode! who-agent :continue)
(set-error-handler! who-agent err-handler-fn)
(send who-agent change-error)
@who-agent
(send who-agent change)





















(comment (-main))