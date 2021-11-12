(ns days.day7
  (:require [utils.helpers :refer [getInput]])
  (:require [clojure.string :as str])
)

; -- PART 1
(defn contain-pair
  "check if string contain four-character sequence"
  [word]
  (loop [index 0]
    (if (< (-(count word) 3) index)
      (identity false)
      (if (and (= (take 2 (drop index word)) (reverse (take 2 (drop (+ 2 index) word)))) (not= (first(take 2 (drop index word))) (second (take 2 (drop index word)))))
        (identity true)
        (recur (inc index))))))

(defn is-support-tls
  [ip]
  (every? true?
    (reduce
      (fn [results indexed-word]
        (def idx (first indexed-word))
        (def word (second indexed-word))
        (if (even? idx)
          (if (false? (first results)) (assoc results 0 (contain-pair word)) results)
          (if (true? (second results)) (assoc results 1 (not (contain-pair word))) results)
      ))
      [false, true]
      (map-indexed (fn [idx word] [idx word]) (str/split ip #"[\[|\]]")))))

(defn do-part-1
  "How many IPs in your puzzle input support TLS?"
  [text-list]
  (println "Part 1: " (count (filter true? (map #(is-support-tls %) text-list )))))

;110

; -- PART 2

(defn is-aba
  [abc]
  (and
    (= (first abc) (last abc))
    (not= (first abc) (second abc))))

(defn get-sequences
  [word]
  (loop [index 0 result []]
    (if (< (-(count word) 3) index)
      (identity result)
      (do
        (def letter-sequence (take 3 (drop index word)))
        (if (is-aba letter-sequence)
          (recur (inc index) (conj result letter-sequence))
          (recur (inc index) result)
        )
      )))) 

(defn aba-to-bab
  [aba]
  (str/join "" (vector (second aba) (first aba) (second aba)))
)

(defn word-include-sequence
  [bab-list word]
  (some true? (map #(str/includes? word %) bab-list))
)

(defn is-support-ssl
  [ip]
  (def word-collection (str/split ip #"[\[|\]]"))
  (def bab-list (map aba-to-bab (mapcat get-sequences (take-nth 2 word-collection))))
  (if (some true? (map #(word-include-sequence bab-list %) (take-nth 2 (rest word-collection)))) true false)
)

(defn do-part-2
  "How many IPs in your puzzle input support SSL?"
  [text-list]
  (println "Part 2: " (count (filter true? (map #(is-support-ssl %) text-list ))))
)
;242


(defn runDay7
  "--- Day 7: Internet Protocol Version 7 ---"
  []
  (println "-- Day 7 --")
  (def text-list (getInput "puzzleInput/day7.txt"))
  (do-part-1 text-list)
  (do-part-2 text-list)
)