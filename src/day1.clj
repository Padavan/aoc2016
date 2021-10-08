(ns day1
  (:require [clojure.string :as str])
  (:require [helpers :refer [getInput]])
)

(defn runDay1 []

  (println "day1")
  
  (def coordList (str/split (first (getInput "puzzleInput/test-input-day1.txt")) #", "))

  ; (def final-destination 
  ;   (reduce 
  ;     (fn [current indexedCoordVector]
  ;        ; (println current)
  ;        (def index (get indexedCoordVector 0))
  ;        (def coord (get indexedCoordVector 1))
  ;        (def side (get current 2))
  ;        (def diff-length (Integer/parseInt (subs coord 1 (count coord))))
  ;        (def isRight (= (first coord) \R))
  ;        (println current (if (odd? index) "y" "x") coord)
  ;        ; (println (= (first coord) \R))
  ;        (cond
  ;          (= side "North")
  ;            (if isRight
  ;              (vector (+ (get current 0) diff-length) (get current 1) "East")
  ;              (vector (- (get current 0) diff-length) (get current 1) "West" )
  ;            )
  ;          (= side "East")
  ;            (if isRight
  ;              (vector (get current 0) (- (get current 1) diff-length) "South")
  ;              (vector (get current 0) (+ (get current 1) diff-length) "North" )
  ;            )
  ;           (= side "South")
  ;            (if isRight
  ;              (vector (- (get current 0) diff-length) (get current 1) "West")
  ;              (vector (+ (get current 0) diff-length) (get current 1) "East" )
  ;            )
  ;           (= side "West")
  ;            (if isRight
  ;              (vector (get current 0) (+ (get current 1) diff-length) "North")
  ;              (vector (get current 0) (- (get current 1) diff-length) "South" )
  ;            )
  ;            :else current 
  ;          ) 
  ;     )
  ;    (vector 0 0 "North")
  ;    (map-indexed vector coordList))
  ;  )

  ; (println final-destination)
  ; (println (+ (Math/abs (get final-destination 0)) (Math/abs(get final-destination 1)) ))

  ; (defn populate-pos-between
  ;   "return list with coordinates between two vector positions"
  ;   [vector1 vector2]
  ;     (println "vectors" vector1 vector2)
  ;     (def xrange (range (first vector1) (first vector2) (if (> (first vector1) (first vector2)) 1 -1)))
  ;     (def yrange (range (second vector1) (second vector2) (if (> (second vector1) (first vector2)) 1 -1)))
  ;     (println xrange yrange)

  ;     (conj
  ;       (for [x yrange] 
  ;         (vector x (second vector1) (get 2 vector2))
  ;       )
  ;       (for [y yrange]
  ;         (vector (first vector1) y (get vector2 2))
  ;       )
  ;     )
  ; )

  ; (println (populate-pos-between (vector 0 0 "north") (vector 0 -5 "north")))

  (def positions-list 
    (reduce 
      (fn [posList indexedCoordVector]
         (println posList)
         (def current (last posList))
         (def index (get indexedCoordVector 0))
         (def coord (get indexedCoordVector 1))
         (def side (get current 2))
         (def diff-length (Integer/parseInt (subs coord 1 (count coord))))
         (def delta-range (range 1 (inc diff-length)))
         (def isRight (= (first coord) \R))
         (println current (if (odd? index) "y" "x") coord)
         ; (println )
         ; (conj posList
         (println
           (cond
             (= side "North") (for [delta-x delta-range] (
                  ; (println delta-x)
                  ; (def newPoint 
                  (vector ((if isRight + -) (first current) delta-x) (second current) (if isRight "East" "West"))
                  ; )
                  ; (println "newPoint" newPoint)
                  ; (if (.contains posList newPoint) (println newPoint "Finish") newPoint)
              ))
             (= side "East")
                 for [delta-y delta-range] (
                  ; (def newPoint (
                  vector (first current) ((if isRight - +) (second current) delta-y) (if isRight "South" "North"))
                 ; )
                  ; (println "newPoint" newPoint)
                  ; (if (.contains posList newPoint) (println newPoint "Finish") newPoint)
                  ; )
              (= side "South")
                for [delta-x delta-range] (
                  ; (def newPoint (
                  vector ((if isRight - +) (first current) delta-x) (second current) (if isRight "West" "East"))
                ; )
                  ; (println "newPoint" newPoint)
                  ; (if (.contains posList newPoint) (println newPoint "Finish") newPoint)
                ; )
              (= side "West")
               for [delta-y delta-range] (
                  ; (def newPoint (
                  vector (first current) ((if isRight + -) (second current) delta-y) (if isRight "North" "South"))
               ; )
                  ; (println "newPoint" newPoint)
                  ; (if (.contains posList newPoint) (println newPoint "Finish") newPoint)
                ; )
           )
         )
      )
     (list (vector 0 0 "North"))
     (map-indexed vector coordList))
   )

)

; 1 attempts
; 118 too low
; 400 
; 394
; 476
; 242

; 2 attemps
