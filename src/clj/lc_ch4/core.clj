(ns lc_ch4.core
  (:import (java.net InetAddress)))

(defn -main []
  (println "Living Clojure Chapter 4"))

;; Java interop and Polymorphism
(class "caterpillar")
(. "caterpillar" toUpperCase)
(.indexOf "caterpillar" "pillar")
(new String "Hi!!")
(String. "Hi!!")
(InetAddress/getByName "localhost")
(.getHostName (InetAddress/getByName "localhost"))
(java.net.InetAddress/getByName "localhost")
(def sb (doto (StringBuffer. "Who ")
          (.append "are ")
          (.append "you?")))
(.toString sb)
(import 'java.util.UUID)
(UUID/randomUUID)
; Practical Plymorphism
(defn who-are-you [input]
  (cond
    (= java.lang.String (class input)) "String - Who are you?"
    (= clojure.lang.Keyword (class input)) "Keyword - Who are you?"
    (= java.lang.Long (class input)) "Number - Who are you?"))

(who-are-you :alice)
(who-are-you "alice")
(who-are-you 123)
(who-are-you true)

(defmulti who-are-you class)
(defmethod who-are-you java.lang.String
 [input]
 (str "String - who are you? " input))

(defmethod who-are-you clojure.lang.Keyword
  [input]
  (str "Keyword - who are you? " input))

(defmethod who-are-you java.lang.Long
  [input]
  (str "Number = who are you?" input))

(who-are-you :alice)
(who-are-you "Alice")
(who-are-you 1234)
(who-are-you true)

(defmethod who-are-you :default
 [input]
 (str "I don't know - who you are? " input))

(who-are-you true)

(defmulti eat-mushroom (fn [height]
                         (if (< height 3)
                           :grow
                           :shrink)))
(defmethod eat-mushroom :grow [_]
  "Eat the right side to grow")
(defmethod eat-mushroom :shrink [_]
  "Eat the left side to shrink")

(eat-mushroom 1)
(eat-mushroom 9)

(defprotocol BigMushroom
  (eat-mushroom [this]))

(extend-protocol BigMushroom
  java.lang.String
  (eat-mushroom [this]
    (str (.toUpperCase this) " mmm tasty!"))
  clojure.lang.Keyword
  (eat-mushroom [this]
    (case this
      :grow "Eat the right side!"
      :shrink "Eat the left side!"))
  java.lang.Long
  (eat-mushroom [this]
    (if (< this 3)
      "Eat the right side to grow"
      "Eat the left side to shrink")))

(eat-mushroom "Big Mushroom")
(eat-mushroom :grow)
(eat-mushroom 1)

(defrecord Mushroom [color height])
(def regular-mushroom (Mushroom. "white and blue polkadots" "2 inches"))
(class regular-mushroom)
(.-color regular-mushroom)
(.-height regular-mushroom)

(defprotocol Edible
  (bite-right-side [this])
  (bite-left-side [this]))

(defrecord WonderlandMushroom [color height]
  Edible
  (bite-right-side [this]
    (str "The " color " bite makes you grow bigger"))
  (bite-left-side [this]
    (str "The " color " bit makes you grow smaller")))

(defrecord RegularMushroom [color height]
 Edible
  (bite-right-side [this]
    (str "The " color " bite tastes bad"))
  (bite-left-side [this]
    (str "The " color " bite tastes bad too")))

(def alice-mushroom (WonderlandMushroom. "blue dots" "3 inches"))
(def reg-mushroom (RegularMushroom. "brown" "1 inches"))

(bite-right-side alice-mushroom)
(bite-left-side alice-mushroom)
(bite-right-side reg-mushroom)
(bite-left-side reg-mushroom)

(defprotocol Edible
  (bite-right-side [this])
  (bite-left-side [this]))

(deftype WonderlandMushroom []
 Edible
  (bite-right-side [this]
    (str "The bite makes you grow bigger"))
  (bite-left-side [this]
    (str "The bite makes you grow smaller")))

(deftype RegularMushroom []
  Edible
  (bite-right-side [this]
    (str "The bite tastes bad"))
  (bite-left-side [this]
    (str "The bite tastes bad too")))

(def alice-mushroom (WonderlandMushroom.))
(def reg-mushroom (RegularMushroom.))

(bite-right-side alice-mushroom)
(bite-left-side alice-mushroom)
(bite-right-side reg-mushroom)
(bite-left-side reg-mushroom)

(defn bite-right-side [mushroom]
  (if (= (:type mushroom) "wonderland")
    "The bite makes you grow bigger"
    "The bite tastes bad"))

(defn bite-left-side [mushroom]
  (if (= (:type mushroom) "wonderland")
    "The bite makes you grow smaller"
    "The bite tastes bad too"))

(bite-right-side {:type "wonderland"})
(bite-left-side {:type "regular"})












(comment (-main))