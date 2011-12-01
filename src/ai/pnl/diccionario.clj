(ns ai.pnl.diccionario
   (:use [clojure.set :only [intersection]]))

(defn saltosacoll [s] (re-seq #"[^\n]+" s))

(defn word [s] (re-seq #"[^ ]+"  s))

(defn words [coll] (flatten (map word coll))) ;ahora con contador para las repetidas

(defn countreps [coll]
  (loop [count {} faltan coll]
    (if (empty? faltan) count
      (recur (assoc count (first faltan) (if-let [n (count (first faltan))] (inc n) 1)) (rest faltan)))))

(defn wordsfn [coll] (countreps (words coll)))

(defn filter-intersection [a b]
  (let [inter (intersection (set (keys a)) (set (keys b)))]
    (list (select-keys a inter) (select-keys b inter))))

(defmacro intersect-counters [maps] `(merge-with min ~@maps))

(defn saltosacoll [s] (re-seq #"[^\n]+" s))
