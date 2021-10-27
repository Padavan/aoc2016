(ns days.day5_test
  (:use [clojure.test :refer :all]
        [advent.core :refer :all]
        [days.day5 :refer [get-hash check-hash-include-string]])
)

(deftest test-get-hash
  (testing "Should return correct hash"
    (= (get-hash "test") "098f6bcd4621d373cade4e832627b4f6")
  )
)

(deftest test-check-hash-include-string
  (testing " should return true"
    (true? (check-hash-include-string "000008f82" "00000"))
  )

  (testing " should return false"
    (false? (check-hash-include-string "00008f82" "00000"))
  )
)
