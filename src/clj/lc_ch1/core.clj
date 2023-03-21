(ns lc_ch1.core)

(defn -main []
  (println "Living Clojure Chapter 1"))

;; Bay steps with simple values
12.43 
1/3 
4/2 
4.0/2
(/ 1 3)
(/ 1 3.0)
"jam"
:jam  
\j
true
false
nil
(+ 1 1)
(+ 1(+ 8 3))
'(1 2 "jam" :marmalade-jar)
'(1, 2, "jam", :bee)
(first '(:rabbit :pocket-watch :marmalade :door))
(rest '(:rabbit :pocket-watch :marmalade :door))
(first(rest '(:rabbit :pocket-watch :marmalade :door)))
(first(rest(rest '(:rabbit :pocket-watch :marmalade :door))))
(first(rest(rest(rest '(:rabbit :pocket-watch :marmalade :door)))))
(first(rest(rest(rest(rest '(:rabbit :pocket-watch :marmalade :door))))))
(cons 5 '())
(cons 5 nil)
(cons 4 (cons 5 nil))
(cons 3 (cons 4 (cons 5 nil)))
(cons 2 (cons 3 (cons 4 (cons 5 nil))))
'(1 2 3 4 5)
[:jar 1 2 3 :jar2]
(first [:jar1 1 2 3 :jar2])
(rest [:jar1 1 2 3 :jar2])
(nth [:jar1 1 2 3 :jar2] 0)
(nth [:jar1 1 2 3 :jar2] 1)
(last [:rabbit :pocket-watch :marmalade])
(last '(:rabbit :pocket-watch :marmalade))
(count [1 2 3 4])
(conj [:toast :butter] :jam)
(conj [:toast :butter] :jam :honey)
(conj '(:toast :butter) :jam)
(conj '(:toast :butter) :jam :honey)
{:jam1 "strawberry" :jam2 "blackberry"}
{:jam1 "starwberry", :jam2 "blackberry"}
(get {:jam1 "strawberry" :jam2 "blackberry"} :jam2)
(get {:jam1 "strawberry" :jam2 "blackberry"} :jam3 "not found")
(:jam2 {:jam1 "starwberry" :jam2 "blackberry" :jam3 "marmalade"})
(keys {:jam1 "strawberry" :jam2 "blackberry" :jam3 "marmalade"})
(vals {:jam1 "strawberry" :jam2 "blackberry" :jam3 "marmalade"})
(assoc {:jam1 "red" :jam2 "black"} :jam1 "orange")
(dissoc {:jam1 "strawberry" :jam2 "blackberry"} :jam1)
(merge {:jam1 "red" :jam2 "black"}
       {:jam1 "orange" :jam3 "red"}
       {:jam4 "blue"})
#{:red :blue :white :pink}
#{:red :blue :white :pink :pink}
(clojure.set/union #{:r :b :w} #{:w :p :y})
(clojure.set/difference #{:r :b :w} #{:w :p :y})
(clojure.set/intersection #{:r :b :w} #{:w :p :y})
(set [:rabbit :rabbit :watch :door])
(set {:a 1 :b 2 :c 3})
(get #{:rabbit :door :watch} :rabbit)
(get #{:rabbit :door :watch} :jar)
(:rabbit #{:rabbit :door :watch})
(#{:rabbit :door :watch} :rabbit)
(contains? #{:rabbit :door :watch} :rabbit)
(contains? #{:rabbit :door :watch} :jam)
(conj #{:rabbit :door} :jam)
(disj #{:rabbit :door} :door)
'("marmalade-jar" "empty-jar" "pikle-jam-jar")
("marmalade-jar" "empty-jar" "pikle-jam-jar")
'(+ 1 1)
(first '(+ 1 1))
;; Code is data !
[1 2 3 4]
(def developer "Alice")
developer
lc_ch1.core/developer
(let [developer "Alice in Wonderland"]
  developer)
developer
(let [developer "Alice in Wonderland"
      rabbit "White Rabbit"]
  [developer rabbit])
rabbit
(defn follow-the-rabbit [] "Off we go!")
(follow-the-rabbit)
(defn shop-for-jams [jam1 jam2]
  {:name "jam-basket"
   :jam1 jam1
   :jam2 jam2})
(shop-for-jams "strawberry" "marmalade")
(fn [] (str "Off we go" "!"))
((fn [](str "Off we go" "!")))
(def follow-again (fn [] (str "Off we go" "!")))
(follow-again)
(#(str "Off we go" "!"))
(#(str "Off we go" "!" " - " %) "again")
(#(str "Off we go" "!" " - " %1 %2) "again" "?")
(ns alice.favfoods)
*ns*
(def fav-food "strawberry jam")












(comment (-main))