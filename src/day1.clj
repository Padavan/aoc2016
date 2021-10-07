(ns day1
  (:require [clojure.string :as str])
  (:require [helpers :refer [getInput]])
)

(defn runDay1 []

  (println "day1")
  
  (def coordList (str/split (first (getInput "puzzleInput/input-day1.txt")) #", "))

  (def final-destination 
    (reduce 
      (fn [current indexedCoordVector]
         ; (println current)
         (def index (get indexedCoordVector 0))
         (def coord (get indexedCoordVector 1))
         (def side (get current 2))
         (def diff-length (Integer/parseInt (subs coord 1 (count coord))))
         (def isRight (= (first coord) \R))
         (println current (if (odd? index) "y" "x") coord)
         ; (println (= (first coord) \R))
         (cond
           (= side "North")
             (if isRight
               (vector (+ (get current 0) diff-length) (get current 1) "East")
               (vector (- (get current 0) diff-length) (get current 1) "West" )
             )
           (= side "East")
             (if isRight
               (vector (get current 0) (- (get current 1) diff-length) "South")
               (vector (get current 0) (+ (get current 1) diff-length) "North" )
             )
            (= side "South")
             (if isRight
               (vector (- (get current 0) diff-length) (get current 1) "West")
               (vector (+ (get current 0) diff-length) (get current 1) "East" )
             )
            (= side "West")
             (if isRight
               (vector (get current 0) (+ (get current 1) diff-length) "North")
               (vector (get current 0) (- (get current 1) diff-length) "South" )
             )
             :else current 
           ) 
      )
     (vector 0 0 "North")
     (map-indexed vector coordList))
   )

  (println final-destination)
  (println (+ (Math/abs (get final-destination 0)) (Math/abs(get final-destination 1)) ))
)


; 118 too low
; 400 
; 394
; 476
; 242

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
