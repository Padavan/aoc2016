(ns utils.helpers
  (:require [clojure.java.io :as io])
)

(defn getInput
  [path]
  (with-open [rdr (io/reader path)]
    (doall (line-seq rdr)))
)