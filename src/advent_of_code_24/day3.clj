(ns advent-of-code-24.day3
  (:require [clojure.java.io :as io]
            [clojure.string :as str]))

(def input
  (-> (io/resource "day3_input.txt")
      slurp))

(def patt
  (re-pattern "mul\\(\\d{1,3},\\d{1,3}\\)"))

(defn sum-instructions
  [input]
  (->> (re-seq patt input)
       (mapv #(str/replace % #"mul\(" "(* "))
       (mapv #(str/replace % #"," " "))
       (mapv #(eval (read-string %)))
       (reduce +)))

(def part-1-result
  (sum-instructions input))


(def part-2-result
  (->> (str/split input #"do\(\)")
       (mapv #(str/split % #"don't\(\)"))
       (mapv first)
       (apply str)
       sum-instructions))

part-1-result
part-2-result

