(ns ai.pnl.synons
  (:use clojure.contrib.combinatorics)
  (:require [clojure.contrib.duck-streams :as rw]
           [clojure.contrib.string :as st]))


(defn tokenizer-by-sentence 
  "Separa por oraciones (cada . o ,)"
  [string]
  (re-seq #"[^.,]+" string))


(defn merge-sentences [sentences]
  (let [nums (distinct (map count  sentences))]
    (for [i nums]
      (apply cartesian-product (vec (apply map vector (filter #(= i (count %)) sentences)))))))

(defn create-sentences [texts]
  (let [clean-text (filter #(not= "" %) (distinct texts))
        sentences (map tokenizer-by-sentence clean-text)
        new-sentences (apply concat (merge-sentences sentences))]
    (map #(st/join "," %) new-sentences)))
