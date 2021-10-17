(ns days.day2
  (:require [utils.helpers :refer [getInput]])
)

; -- PART 1

(defn get-number-for-position
  [pos-vector]
  (def x-pos (first pos-vector))
  (def y-pos (second pos-vector))
  (get (get (vector (vector 1 2 3) (vector 4 5 6) (vector 7 8 9)) y-pos) x-pos)
)

(defn parse-sequence
  "Recursevely parse sequences"
  [sequence start-vector]
  (reduce (fn [xy character]
        (identity (cond
          (= character \L) (if (= (dec (first xy))  -1) xy (assoc xy 0 (dec (first xy))))
          (= character \R) (if (= (inc (first xy))   3) xy (assoc xy 0 (inc (first xy))))
          (= character \U) (if (= (dec (second xy)) -1) xy (assoc xy 1 (dec (second xy))))
          (= character \D) (if (= (inc (second xy))  3) xy (assoc xy 1 (inc (second xy))))
        ))
      )
      start-vector
      sequence
  )
)

(defn do-part-1
  "Get bathroom code"
  [sequenceList]
  (println
  (map get-number-for-position
    (reduce (fn [start-position-list sequence]
      (conj start-position-list (parse-sequence sequence (last start-position-list))))
      (vector (vector 1 1))
      sequenceList
  ))) 
)
; 18843

; -- PART 2

(def keypad-part-2 (vector
  (vector 0 0 0 0 0 0 0)
  (vector 0 0 0 1 0 0 0)
  (vector 0 0 2 3 4 0 0)
  (vector 0 5 6 7 8 9 0)
  (vector 0 0 \A \B \C 0 0)
  (vector 0 0 0 \D 0 0 0)
  (vector 0 0 0 0 0 0 0)
))

(defn get-keypad-symbol
  "Get symbol from keypad for position vector"
  [pos-vector]
  (def x-pos (first pos-vector))
  (def y-pos (second pos-vector))
  (get (get keypad-part-2 y-pos) x-pos)
)

(defn check-key
  "Check if key on keypad exist. Return boolean"
  [pos-vector]
  (if (= 0 (get-keypad-symbol pos-vector)) false true)
)

(defn move-vector
  [pos-vector character]
  (identity (cond
    (= character \L) (assoc pos-vector 0 (dec (first pos-vector)))
    (= character \R) (assoc pos-vector 0 (inc (first pos-vector)))
    (= character \U) (assoc pos-vector 1 (dec (second pos-vector)))
    (= character \D) (assoc pos-vector 1 (inc (second pos-vector)))
  ))
)

(defn parse-sequence-part-2
  "Recursevely parse sequences"
  [sequence start-vector]
  (reduce (fn [posvector character]
      (def new-position-vector (move-vector posvector character))
      (if (check-key new-position-vector) new-position-vector posvector)
    )
    start-vector
    sequence
  )
)

(defn do-part-2
  "Get bathroom code for keypad 2"
  [sequenceList]
  (println "part 2")
  (println
  (map get-keypad-symbol
    (reduce (fn [start-position-list sequence]
      (conj start-position-list (parse-sequence-part-2 sequence (last start-position-list))))
      (vector (vector 1 3))
      sequenceList
  )))
)
; 67BB9

(defn runDay2
  "Read a file and get sequence array"
  []
  (println "Day 2")
  (def instructionData (getInput "puzzleInput/day2.txt"))
  (do-part-1 instructionData)
  (do-part-2 instructionData)
)
