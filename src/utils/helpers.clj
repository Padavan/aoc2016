(ns utils.helpers
  (:require [clojure.java.io :as io])
)

(defn getInput [path]
  (with-open [r (io/reader path)]
    (line-seq r)
  )
)