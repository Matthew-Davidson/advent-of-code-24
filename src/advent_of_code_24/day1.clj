(ns advent-of-code-24.day1
  (:require [clojure.java.io :as io]
            [clojure.string :as str]))

(def input (-> (io/resource "day1_input.txt")
               slurp
               (str/replace #"   " "\t")
               (str/split #"\n")))

(def entries (mapv #(map read-string (str/split % #"\t")) input))
(def left-list (sort (mapv first entries)))
(def right-list (sort (mapv second entries)))

(defn distance-sum [a b]
  (reduce + (map #(abs (- %1 %2)) a b)))

(def distances (distance-sum left-list right-list))

(defn count-if [coll val]
  (-> coll
      frequencies
      (get val)
      (or 0)))

(defn similarity [a b]
  (->> a
       (map #(* % (count-if b %)))
       (reduce +)))

; part 1 answer
distances
; part 2 answer
(similarity left-list right-list)
