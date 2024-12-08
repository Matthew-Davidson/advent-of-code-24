(ns advent-of-code-24.day2
  (:require [clojure.java.io :as io]
            [clojure.string :as str]))

(def input (->>
            (->
             (io/resource "day2_input.txt")
             slurp
             (str/split #"\n"))
            (mapv #(mapv read-string (str/split % #" ")))))


(defn analyse-report [report]
  (let [diffs (map - (drop-last report) (rest report))
        asc-or-desc? (or (every? pos? diffs)
                         (every? neg? diffs))
        diffs<4 (every? #(< % 4) (map abs diffs))
        safe? (and asc-or-desc? diffs<4)]
    safe?))

(defn count-safe-reports [reports]
  (->> reports
       (map analyse-report)
       (filter true?)
       count))

(defn drop-nth
  [coll idx]
  (concat (take idx coll) 
          (drop (inc idx) coll)))

(defn report->reports
  [report]
  (conj
   (mapv
    #(vec (drop-nth %1 %2))
    (repeat (count report) report)
    (range (count report)))
   report))

(defn count-safe-reports-2
  [reports]
  (->> reports
       (mapv report->reports)
       (mapv (fn [report] (some true? (map analyse-report report))))
       (filter true?)
       count))

; part 1 answer
(count-safe-reports input)
; part 2 answer
(count-safe-reports-2 input)


