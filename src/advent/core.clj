(ns advent.core
  (:require [days.day1 :refer [runDay1]])
  (:require [days.day2 :refer [runDay2]])
)

(defn -main
  "I don't do a whole lot."
  []
  (println "Advent of Code 2016!")
  (runDay1)
  (runDay2)
)
