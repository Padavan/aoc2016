(ns day1
  (:require [clojure.string :as str])
  (:require [helpers :refer [getInput]])
)

(defn check-list-for-vector
    "Checking if list contains certain vector without checking cardinal direction"
    [list point]
    (some #(and (= (first point) (first %)) (= (second point) (second %))) list)
)

(defn calculate-distance
  "How far location vector from 0,0"
  [locationVector]
  (+ (Math/abs (first locationVector)) (Math/abs (second locationVector)))
)

(defn do-part-1
  "Where we follow sequence to the end and get final destination."
  [coordList]
  (def final-destination 
  (reduce 
    (fn [current indexedCoordVector]
       (def index (first indexedCoordVector))
       (def coord (second indexedCoordVector))
       (def side (get current 2))
       (def diff-length (Integer/parseInt (subs coord 1 (count coord))))
       (def isRight (= (first coord) \R))
       (println current (if (odd? index) "y" "x") coord)
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
  (println (calculate-distance final-destination))
)

(defn do-part-2
  "Where we follow sequence but stop at the first position we visit twice"
  [coordList]
  (def positions-list 
    (reduce 
      (fn [posList indexedCoordVector]
          ; (println posList)
          (def current (last posList))
          (def index (get indexedCoordVector 0))
          (def coord (get indexedCoordVector 1))
          (def side (get current 2))
          (def diff-length (Integer/parseInt (subs coord 1 (count coord))))
          (def delta-range (range 1 (inc diff-length)))
          (def isRight (= (first coord) \R))
          (concat posList
            (cond
              (= side "North") (for [delta-x delta-range
                :let [newPoint (vector ((if isRight + -) (first current) delta-x) (second current) (if isRight "East" "West"))]]
                (if (check-list-for-vector posList newPoint) (assoc newPoint 2 "STOP") newPoint)
              )
              (= side "East") (for [delta-y delta-range
                :let [newPoint (vector (first current) ((if isRight - +) (second current) delta-y) (if isRight "South" "North"))]]
                (if (check-list-for-vector posList newPoint) (assoc newPoint 2 "STOP") newPoint)
              )
              (= side "South") (for [delta-x delta-range
                :let [newPoint (vector ((if isRight - +) (first current) delta-x) (second current) (if isRight "West" "East"))]]
                (if (check-list-for-vector posList newPoint) (assoc newPoint 2 "STOP") newPoint)
              )
              (= side "West") (for [delta-y delta-range
                :let [newPoint (vector (first current) ((if isRight + -) (second current) delta-y) (if isRight "North" "South"))]]
                (if (check-list-for-vector posList newPoint) (assoc newPoint 2 "STOP") newPoint)
              )
           )
         )
      )
     (list (vector 0 0 "North"))
     (map-indexed vector coordList))
   )

  (println (calculate-distance(first (filter #(= (get % 2) "STOP") positions-list))))
)


(defn runDay1
  "Read a file and get sequence array"
  []

  (def coordList (str/split (first (getInput "puzzleInput/input-day1.txt")) #", "))
  (do-part-1 coordList)
  (do-part-2 coordList)
)

; part 1
; 242

; part 2 
; 150 - right answer position: [134 16 STOP]
