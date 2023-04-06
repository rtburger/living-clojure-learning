(ns lc_ch2.core)

(defn -main []
  (println "Living Clojure Chapter 2"))

(first [1 2 3])
(first [:a :b :c])
(first)
(class true)
(true? true)
(true? false)
(false? false)
(false? true)
(nil? nil)
(nil? 1)
(not true)
(not false)
(not nil)
(not "hi")
(= :drinkme :drinkme)
(= :drinkme 4)
(= '(:drinkme :bottle) [:drinkme :bottle])
(not= :drinkme 4)
(empty? [:table :door :key])
(empty? [])
(empty? {})
(empty? '())
(seq [1 2 3])
(class [1 2 3])
(class (seq [1 2 3]))
(seq [])
(empty? [])
(every? odd? [1 3 5])
(every? odd? [1 2 3 4 5])
(defn drinkable? [x]
  (= x :drinkme))
(every? drinkable? [:drinkme :drinkme])
(every? drinkable? [:drinkme :poison])
(every? (fn [x] (= x :drinkme)) [:drinkme :drinkme])
(every? #(= % :drinkme) [:drinkme :drinkme])
(not-any? #(= % :drinkme) [:drinkme :poison])
(not-any? #(= % :drinkme) [:poison :poison])
(some #(> % 3) [1 2 3 4 5])
(#{1 2 3 4 5} 3)
(some #{3} [1 2 3 4 5])
(some #{4 5} [1 2 3 4 5])
(some #{nil} [nil nil nil])
(some #{false} [false false false])
; harnessing the power of flow control
(if true "it is true" "it is false")
(if false "it is true" "it is false")
(if nil "its is true" "it is false")
(if (= :drinkme :drinkme)
  "Try it"
  "Don't try it")
(let [need-to-grow-small (> 5 3)]
  (if need-to-grow-small
    "drink bottle"
    "don't drink bottle"))
(if-let [need-to-grow-small (> 5 3)] 
  "drink bottle" 
  "don't drink bottle")
(defn drink [need-to-grow-small]
  (when need-to-grow-small "drink bottle"))
(drink true)
(drink false)
(when-let [need-to-grow-small true]
  "drink bottle")
(when-let [need-to-grow-small false]
  "drink bottle")
(let [bottle "drinkme"]
  (cond
    (= bottle "poison") "don't touch"
    (= bottle "drinkme") "grow smaller"
    (= bottle "empty") "all gone"))
(let [x 5]
  (cond
    (> x 10) "bigger than 10"
    (> x 4) "bigger than 4"
    (> x 3) "bigger than 3"))
(let [x 5]
  (cond
    (> x 3) "bigger than 3"
    (> x 10) "bigger than 10"
    (> x 4) "bigger than 4"))
(let [x 1]
  (cond
    (> x 10) "bigger than 10"
    (> x 4) "bigger than 4"
    (> x 3) "bigger than 3"))
(let [bottle "mystery"]
  (cond
    (= bottle "poison") "don't touch"
    (= bottle "drinkme") "grow smaller"
    (= bottle "empty") "all gone"
   :else "unknown"))

(let [bottle "mystery"]
  (cond
    (= bottle "poison") "don't touch"
    (= bottle "drinkme") "grow smaller"
    (= bottle "empty") "all gone"
    "default" "unknown"))

(let [bottle "drinkme"]
  (case bottle
    "poison" "don't touch"
    "drinkme" "grow smaller"
    "empty" "all gone"))

(let [bottle "mystery"]
  (case bottle
    "poison" "don't touch"
    "drinkme" "grow smaller"
    "empty" "all gone"))

(let [bottle "mystery"]
  (case bottle
    "poison" "don't touch"
    "drinkme" "grow smaller"
    "empty" "all gone"
    "unknown"))
; Fucntions Creating Functions and other neat expressions
(defn grow [name direction]
  (if (= direction :small)
    (str name " is growing smaller")
    (str name " is growing bigger")))

(grow "Alice" :small)
(grow "Alice" :big)
(partial grow "Alice")
((partial grow "Alice") :small)

(defn toggle-grow [direction]
  (if (= direction :small) :big :small))
(toggle-grow :big)
(toggle-grow :small)

(defn oh-my [direction]
  (str "oh my! You are growing " direction))

(oh-my (toggle-grow :small))

(defn surprise [direction]
  ((comp oh-my toggle-grow) direction))

(surprise :small)

(defn adder [x y]
  (+ x y))

(adder 3 4)
(def adder-5 (partial adder 5))

(adder-5 10)

; destructuring
(let [[color size]["blue" "small"]]
  (str "the " color " door is " size))
(let [x ["blue" "small"]
      color (first x)
      size (last x)]
  (str "The " color " door is " size))

(let [[color [size]] ["blue" ["very small"]]]
  (str "The " color " door is " size))

(let [[color [size] :as original] ["blue" ["small"]]]
  {:color color :size size :original original})

(let [{flower1 :flower1 flower2 :flower2}
      {:flower1 "red" :flower2 "blue"}]
  (str "The flowers are " flower1 " and " flower2))

(let [{flower1 :flower1 flower2 :flower2 :or {flower2 "missing"}}
      {:flower1 "red"}]
  (str "The flowers are " flower1 " and " flower2))

(let [{flower1 :flower1 :as all-flowers}
      {:flower1 "red"}]
  [flower1 all-flowers])
(let [{:keys [flower1 flower2]}
      {:flower1 "red" :flower2 "blue"}]
  (str "The flowers are " flower1 " and " flower2))

(defn flower-colors [colors]
  (str "The flowers are "
       (:flower1 colors)
       " and "
       (:flower2 colors)))
(flower-colors {:flower1 "red" :flower2 "blue"})

(defn flower-colors [{:keys [flower1 flower2]}]
  (str "The flowers are " flower1 " and " flower2))

(flower-colors {:flower1 "red" :flower2 "blue"})

; The Power of Laziness
(take 5 (range))
(take 10 (range))
(range 5)
(class (range )) ;; no longer returns a LazySeq, rather a LongRange instead
(realized? (range))
(range)
(+ 1 1)
(take 10 (range))
(count (take 1000 (range)))
(repeat 3 "rabbit")
(class (repeat 3 "rabbit")) ;; doesnt appear lazy either
(class (take 5 (iterate inc 0))) ;; this is Lazy !
(repeat 3 "rabbit")
(class (repeat 3 "rabbit"))
(take 5 (repeat "rabbit"))
(count (take 5000 (repeat "rabbit")))
(rand-int 10)
(rand-int 10)
(repeat 5 (rand-int 10))
#(rand-int 10)
(#(rand-int 10))
(repeatedly 5 #(rand-int 10))
(take 10 (repeatedly #(rand-int 10)))
(take 3 (cycle ["big" "small"]))
(take 6 (cycle ["big" "small"]))
(take 3 (rest (cycle ["big" "small"])))
;; Recursion
(def adjs ["normal"
           "too small"
           "too big"
           "is swimming"])

(defn alice-is [in out]
  (if (empty? in) ; we check to see if the input vector is empty
    out           ; if so, we are done processing ans we are ready to returnt he result
    (alice-is     ; if we are not done, we start over and call the same function with different inputs
     (rest in)    ; In the first position, we give it the rest of the original input
     (conj out    ; In the second position, we apply the string to the element and then append the result to the output vector
           (str "Alice is " (first in))))))                                    

(alice-is adjs [])

(defn alice-is2 [input]
  (loop [in input out []]
    (if (empty? in)
      out
      (recur (rest in)
             (conj out
                   (str "Alice is " (first in)))))))

(alice-is2 adjs)

(defn countdown [n]
  (if (= n 0)
    n
    (recur (- n 1))))

(countdown 3)

(countdown 10000)

; The Functional Shape of Data Transformations
; Map the Ultimate
(def animals [:mouse :duck :dodo :lory :eaglet])
animals
(#(str %) :mouse)
(map #(str %) animals)
(class (map #(str %) animals) )
(take 3 (map #(str %) (range)))
(take 10 (map #(str %) (range)))
(println "Look at the mouse!")
(def animal-print (map #(println %) animals))
animal-print
(def animal-print (doall (map #(println %) animals)))
animal-print
(def animals 
  ["mouse" "duck" "dodo" "lory" "eaglet"])
(def colors
  ["brown" "black" "blue" "pink" "gold"])
(defn gen-animal-string [animal color]
  (str color "-" animal))

(map gen-animal-string animals colors)
(def animals
  ["mouse" "duck" "dodo" "lory" "eaglet"])
(def colors
  ["brown" "black"])
(map gen-animal-string animals colors)
(map gen-animal-string animals (cycle ["brown" "black"]))

; Reduce the Ultimate
(reduce + [1 2 3 4 5])
(reduce (fn [r x] (+ r (* x x))) [1 2 3])
(reduce (fn [r x] (if (nil? x) r (conj r x)))
        []
        [:mouse nil :duck nil nil :lory])

; Other Useful Data Shaping Expressions
((complement nil?) nil)
((complement nil?) 1)
(filter (complement nil?) [:mouse nil :duck nil])
(filter keyword? [:mouse nil :duck nil])
(remove nil? [:mouse nil :duck nil])
(for [animal [:mouse :duck :lory]]
  (str (name animal)))

(for [animal [:mouse :duck :lory]
      color [:red :blue]]
  (str (name color) (name animal)))

(for [animal [:mouse :duck :lory]
      color [:red :blue]
      :let [animal-str (str "animal-"(name animal))
            color-str (str "color-"(name color))
            display-str (str animal-str "-" color-str)]]
  display-str)

(flatten [[:duck [:mouse] [[:lory]]]])
(vec '(1 2 3))
(into [] '(1 2 3))
(sorted-map :b 2 :a 1 :z 3)
(into (sorted-map) {:b 2 :c 3 :a 1})
(into {} [[:a 1] [:b 2] [:c 3]])
(partition 3 [1 2 3 4 5 6 7 8 9 10])
(partition-all 3 [1 2 3 4 5 6 7 8 9 10])
(partition-by #(= 6 %) [1 2 3 4 5 6 7 8 9 10])









(comment (-main))