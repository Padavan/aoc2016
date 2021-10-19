(ns days.day3_test
  (:use [clojure.test :refer :all]
        [advent.core :refer :all]
        [days.day3 :refer [is-triangle-list-valid]])
)

(deftest test-is-triangle-list-valid
  (testing "Should return false (invalid) for triangle where sum of two side less than third"
    (false? (is-triangle-list-valid (vector 10 5 1))))

  (testing "Should return true if triangle possible"
    (true? (is-triangle-list-valid (vector 10 5 1))))

  (testing "Should return false if sum of two side is equal third side"
    (true? (is-triangle-list-valid (vector 2 2 4))))
)


