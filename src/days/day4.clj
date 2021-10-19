(ns days.day4
  (:require [utils.helpers :refer [getInput]])
  (:require [clojure.string :as str]))

; -- PART 1

(defn letter-comparator
  [a b]
  (let [c (compare (second b) (second a))]
    (if (not= c 0)
      c
      (compare (first a) (first b))
    )
  )
)

(defn get-letter-map
  "Get a map structure with letter as key and quantity as value"
  [name]
  (reduce (fn [char-mapping char]
      (if (contains? char-mapping char)
        (assoc char-mapping char (inc (get char-mapping char)))
        (assoc char-mapping char 1)
      )
    )
    {}
    (seq (char-array (filter #(not (= \- %)) (first (str/split name #"\d")))))
  )
)

(defn calculate-checksum
  "Sort letter map and return new checksum"
  [name]
  (apply str (take 5(map #(first %) (sort letter-comparator (get-letter-map name)))))
)

(defn get-checksum
  "Get checksum string from encrypted name"
  [name]
  (def bracket-checksum (re-find (re-pattern "\\[.*?\\]") name))
  (subs bracket-checksum 1 (dec (count bracket-checksum)))
)

(defn is-valid
  "Check if encrypted name valid"
  [name]
  (= (get-checksum name) (calculate-checksum name))
)

(defn get-sector-id
  [name]
  (Integer/parseInt (re-find #"\d+" name))
)

(defn do-part-1
  "What is the sum of the sector IDs of the real rooms?"
  [encrypted-name-list]
  (println "Part 1")

  (println
    (reduce (fn [acc current-name]
        (println current-name (is-valid current-name))
        (if (is-valid current-name) (+ acc (get-sector-id current-name)) acc)
      )
      0
      encrypted-name-list
    )
  )
)
;409147 --right answer

; -- PART 2

(def alphabet (map-indexed vector (list \a \b \c \d \e \f \g \h \i \j \k \l \m \n \o \p \q \r \s \t \u \v \w \x \y \z)))

(defn rotate-characters
  [shift character]
  ; (println shift character)
  (def new-shift (mod shift 26))
  ; (println "new-shift" new-shift)
  (def index (first (first (filter #(= character (second %)) alphabet))))
  (if (nil? index) \space (second (first (filter #(= (mod (+ index shift) 26) (first %)) alphabet))))
)

(defn rotate-name
  [name]
  (def number-of-shift (get-sector-id name))
  (apply str (map #(rotate-characters number-of-shift %) (seq (char-array (first (str/split name #"\d"))))))
)

(defn do-part-2
  "What is the sector ID of the room where North Pole objects are stored?"
  [encrypted-name-list]
  (println "Part2")
  (println (filter #(str/includes? (rotate-name %) "northpole") encrypted-name-list))
)
; 991 right-answer


(defn runDay4
  "Read a file and get list of encrypted names"
  []
  (println "Day 4")
  (def encrypted-name-list (getInput "puzzleInput/day4.txt"))
  (do-part-1 encrypted-name-list)
  (do-part-2 encrypted-name-list)
)
