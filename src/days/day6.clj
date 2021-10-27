(ns days.day6
  (:require [utils.helpers :refer [getInput]])
  (:require [clojure.string :as str])
)

; -- PART 1
(defn transpose [m]
  "rotate matrix"
  (apply mapv vector m))

(defn get-letter-map
  "Get a map structure with letter as key and quantity as value"
  [line]
  ; (println "line" line)
  (sort-by :value (reduce (fn [char-mapping char]
      (if (contains? char-mapping char)
        (assoc char-mapping char (inc (get char-mapping char)))
        (assoc char-mapping char 1)
      )
    )
    {}
    line
  ))
)

(defn get-character-from-ranged-map-with-max-value
  [char-map]
  ; (println "char-map" char-map)
  (key (apply max-key val char-map))
)

(defn do-part-1
  "what is the error-corrected version of the message being sent?"
  [text-list]
  (println (apply str (map #(get-character-from-ranged-map-with-max-value (get-letter-map %)) (transpose text-list))))
)
; tkspfjcc

; -- PART 2 -- 

(defn get-character-from-ranged-map-with-min-value
  [char-map]
  (key (apply min-key val char-map))
)

(defn do-part-2
  "what is the original message that Santa is trying to send"
  [text-list]
  (println (apply str (map #(get-character-from-ranged-map-with-min-value (get-letter-map %)) (transpose text-list))))
)
; xrlmbypn

(defn runDay6
  "--- Day 6: Signals and Noise ---"
  []
  (println "-- Day 6 --")
  (def text-list (getInput "puzzleInput/day6.txt"))
  ; (do-part-1 text-list)
  (do-part-2 text-list)
)
