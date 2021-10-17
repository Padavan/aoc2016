(ns days.day4
  (:require [utils.helpers :refer [getInput]])
  (:require [clojure.string :as str]))


; -- PART 1

; TODO is-decoy
; TODO get-checksum
; TODO get map letter: quantity
; TODO filter that map by: popularity then alphabetically https://clojure.org/guides/comparators

(defn do-part-1
  "What is the sum of the sector IDs of the real rooms?"
  [encrypted-name-list]
  (println "Part 1")
  (println take 3 encrypted-name-list)
)

(defn runDay4
  "Read a file and get triangles array"
  []
  (println "Day 4")
  (def encrypted-name-list (getInput "puzzleInput/day3.txt"))
  (do-part-1 encrypted-name-list)
  ; (do-part-2 triangles-list)
)
