(ns days.day7_test
  (:use [clojure.test :refer :all]
        [advent.core :refer :all]
        [days.day7 :refer [contain-pair is-support-tls]])
)

(deftest test-contain-pair
  (testing "Contain pair, should return true"
    (is (true? (contain-pair "abba"))))

  (testing "Should return false. same character"
    (is (false? (contain-pair "bbbb"))))

  (testing "Should return false, random characters"
    (is (false? (contain-pair "shit"))))

  (testing "Should return false, not enough characters to pair"
    (is (false? (contain-pair "two"))))
)


(deftest test-is-support-tls
  (testing "should support tls"
    (is (true? (is-support-tls "abba[mnop]qrst"))))

  (testing "Should return false. bracket containt pair"
    (is (false? (is-support-tls "abcd[bddb]xyyx"))))

  (testing "Should return false. doesnt have correct pair"
    (is (false? (is-support-tls "aaaa[qwer]tyui"))))

  (testing "Should return true, there are correct pair"
    (is (true? (is-support-tls "ioxxoj[asdfgh]zxcvbn"))))
)