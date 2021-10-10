(ns days.day1_test
  (:use [clojure.test :refer :all]
        [advent.core :refer :all]
        [days.day1 :refer [calculate-distance]])
)

(deftest test-calculate-distance
  (testing "Should return zero from zero vector."
    (is (= (calculate-distance (vector 0 0)) 0))
  )
)