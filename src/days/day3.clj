(ns days.day3
  (:require [utils.helpers :refer [getInput]])
  (:require [clojure.string :as str]))

; --PART 1

(defn is-triangle-list-valid
  ""
  [number-list]
  (def sorted-list (sort number-list))
  ; (println sorted-list)
  ; (println "first" (first sorted-list))
  ; (println "second" (second sorted-list))
  ; (println "last" (last sorted-list))
  (if (
    or (<= (+ (first sorted-list) (second sorted-list)) (last sorted-list))
    (<= (+ (first sorted-list) (last sorted-list)) (second sorted-list))
    (<= (+ (second sorted-list) (last sorted-list)) (first sorted-list))
  ) false true)
)

(defn is-triangle-valid
  "Parse string and return boolean"
  [triangle-string]
  (def is-valid (is-triangle-list-valid (map #(Integer/parseInt %) (filter #(not (clojure.string/blank? %))
    (str/split triangle-string #" "))
  )))
  (println triangle-string is-valid)
  (identity is-valid)
)

(defn do-part-1
  "Calculate amount of valid triangles"
  [triangles-list]
  (println "Part 1")
  (println (reduce
    (fn [accumulator triangle-string]
      (if (is-triangle-valid triangle-string) (inc accumulator) accumulator)
    )
    0
    triangles-list
  ))
)
; 1032 -right answer

(defn string-to-list-number
  [number-string]
  (map #(Integer/parseInt %)
    (filter #(not (str/blank? %)) (str/split number-string #" "))
  )
)

(defn transpose [m]
  "rotate matrix"
  (apply mapv vector m))

; -- PART 2
(defn do-part-2
  "Calculate amount of valid triangles"
  [triangles-list]
  (println "Part 2")

  (def processed-list
    "prepared list with list of triangles numbers"
    (partition 3
      (flatten
        (transpose
          (map #(string-to-list-number %) triangles-list)
        )
      )
    )
  )


  (println (reduce
    (fn [accumulator triangle-data]
      (if (is-triangle-list-valid triangle-data) (inc accumulator) accumulator)
    )
    0
    processed-list
  ))
)

;1838 - right answer

(defn runDay3
  "Read a file and get triangles array"
  []
  (println "Day 3")
  (def triangles-list (getInput "puzzleInput/day4.txt"))
  (do-part-1 triangles-list)
  (do-part-2 triangles-list)
)
