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
  ; (println
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

(defn get-sequences
  [word]
  (println word)
  (loop [index 0 result []]
    (if (< (-(count word) 2) index)
      (do (println index))
      (recur (inc index) result)
  )
) 

(defn is-support-ssl
  [ip]
  (println ip)
  (def word-collection (str/split ip #"[\[|\]]"))
  (println "odd: " (take-nth 2 word-collection))
  (println "2: " (take-nth 2 (rest word-collection)))
)

(defn do-part-2
  "How many IPs in your puzzle input support SSL?"
  [text-list]
  (is-support-ssl "aba[bab]xyz")
  ; (println "Part 2: " (count (filter true? (map #(is-support-tls %) text-list ))))
)


(defn runDay7
  "--- Day 7: Internet Protocol Version 7 ---"
  []
  (println "-- Day 7 --")
  (def text-list (getInput "puzzleInput/day7.txt"))
  ; (do-part-1 text-list)
  (do-part-2 text-list)
)
