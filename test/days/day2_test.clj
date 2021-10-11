(ns days.day2_test
  (:use [clojure.test :refer :all]
        [advent.core :refer :all]
        [days.day2 :refer [get-digit]])
)

(deftest test-calculate-distance
  (testing "Should return zero from zero vector."
    (is (= (get-digit ("ULL")) 1))
  )
)