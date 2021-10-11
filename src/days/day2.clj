(ns days.day2
  (:require [utils.helpers :refer [getInput]])
  (:require [clojure.string :as str]))


; (defn get-digit
;   "Get digit from matrix based on sequence"
;   [sequence]
;   (println "sequence" sequence)
; )

(defn do-part-1
  "Get bathroom code"
  [sequence]
  (println sequence)
  (def charList (seq (char-array sequence)))

  (println (reduce (fn [xy character] (
    (cond
    (= character \L) (assoc xy 0 (if (= (dec (first xy)) -1) (first xy) (dec (first xy))))
    (= character \R) (assoc xy 0 (if (= (inc (first xy)) 3) (first xy) (inc (first xy))))
    (= character \U) (assoc xy 1 (if (= (dec (second xy)) -1) (second xy) (dec (second xy))))
    (= character \D) (assoc xy 1 (if (= (inc (second xy)) 3) (second xy) (inc (second xy))))
  ))) (vector 1 1) charList))
)

(defn runDay2
  "Read a file and get sequence array"
  []
  (println "Day 2")
  (def instructionData (getInput "puzzleInput/test-input-day2.txt" do-part-1))
  ; (do-part-1 instructionData)
  ; (println "instructionData" instructionData)
  ; (do-part-2 coordList)
)


; (defn total-salary [path]
;   (with-open [rdr (io/reader path)]
;     (let [[header & body] (line-seq rdr)
;           col (.indexOf
;                 (str/split header #",")
;                 "Salary")]
;       (->> body
;         (map #(str/split % #","))
;         (map #(nth % col))
;         (map #(Double/parseDouble %))
;         (sort)
;         (reverse)
;         (take 10)))))