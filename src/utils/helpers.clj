(ns utils.helpers
  (:require [clojure.java.io :as io])
)

(defn getInput
  [path f]
  (with-open [rdr (io/reader path)]
    ; (line-seq r))
    (doall (map f (line-seq rdr))))
)