(ns days.day5
  (:require [utils.helpers :refer [getInput]])
  (:require [clojure.string :as str])
  (:import [java.security MessageDigest])
  (:import [java.math BigInteger])
)

; -- PART 1
(defn get-hash
  "return md5 hash of string"
  [line]
  ; (println line)
  (let [algorithm (MessageDigest/getInstance "MD5") raw (.digest algorithm (.getBytes line))]
    (format "%032x" (BigInteger. 1 raw))
  )
)

(defn check-hash-include-string
  "checking if hash started with certain string"
  [hash search-string]
  (str/starts-with? hash search-string)
)

(defn do-part-1
  "Given the actual Door ID, what is the password?"
  [door-id]
  (loop [index 0 password ""]
    (if (= (count password) 8)
      (println "Part1: " password)
      (do
        (def md5hash (get-hash (str door-id index)))
        (def new-password (if (str/starts-with? md5hash "00000") (str password (get md5hash 5)) password))
        (recur (inc index) new-password)
      )
    )
  )
)
; 4543c154

(defn parse-number
  "Reads a number from a string. Returns nil if not a number."
  [s]
  (if (re-find #"^-?\d+\.?\d*$" s)
    (read-string s)))

; -- PART 2 --
(defn do-part-2
  "Given the actual Door ID, what is the password?"
  [door-id]
  (loop [index 0 password (vector nil nil nil nil nil nil nil nil)]
    (if (not (some nil? password))
    ; (if (= count 6000000)
      (println "Part2: " (apply str password))
      (do
        ; (println password index)
        (def md5hash (get-hash (str door-id index)))
        (def new-password
          (if (and (str/starts-with? md5hash "00000"))
          ; (< position-index 8) (nil? (get password position-index)) (not (nil? position-index))
            (do
              (def position-index (get md5hash 5))
              (def position-parsed (parse-number (str position-index)))
              (if (and (not (nil? position-parsed)) (< position-parsed 8) (nil? (get password position-parsed)))
                (assoc password position-parsed (get md5hash 6))
                password)
            )
            password
          )
        )
        (recur (inc index) new-password)
      )
    )
  )
)
; 


(defn runDay5
  "Run parts. Puzzle input already inside"
  []
  (println "-- Day 5 --")
  (def puzzleInput "ojvtpuvg")
  ; (def puzzleInput "abc")
  ; (do-part-1 puzzleInput)
  (do-part-2 puzzleInput)
)
