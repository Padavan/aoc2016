(ns days.day2_test
  (:use [clojure.test :refer :all]
        [advent.core :refer :all]
        [days.day2 :refer [get-keypad-symbol check-key move-vector]])
)

(deftest test-get-keypad-symbol
  (testing "Should return starting position 5"
    (is (= (get-keypad-symbol (vector 1 3)) 5)))

  (testing "Should return character D"
    (is (= (get-keypad-symbol (vector 3 5)) \D)))

  (testing "Edge cases return 0"
    (is (= (get-keypad-symbol (vector 0 5)) 0)))
)

(deftest test-check-key
  (testing "Should return true in starting position where 5 is located"
    (is (true? (check-key (vector 1 3)))))

  (testing "Should return true in position where D is located"
    (true? (check-key (vector 3 5))))

  (testing "Should return false at edges"
    (false? (check-key (vector 0 0))))

  (testing "Should return false at edges"
    (false? (check-key (vector 6 6))))

  (testing "Should return false at edges"
    (false? (check-key (vector 3 0))))
)

(deftest test-move-vector
  (testing "Should return 1 0"
    (is (= (move-vector (vector 0 0) \R) (vector 1 0))))

)
