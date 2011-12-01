(ns ai.arbol
  (:use [clojure.contrib.string :only [substring?]]))

(defn la-genero? [f]
     (substring? "fm.arbol$arbol" (str (class f))))

(defn arbol
  ([pred v fal]
   (fn [coll]
     (if (pred coll)
       (if (fn? v) (v coll) v)
       (if (fn? fal) (fal coll) fal))))
  ([pred v fal coll]
   (if (pred coll)
     (if (fn? v) (v coll) v)
     (if (fn? fal) (fal coll) fal))))

; a y b. son la partes de arbol por que estan aparte?
(defn a [pred v fal]
  (fn [coll]
    (if (pred coll)
      (if (fn? v) (v coll) v)
      (if (fn? fal) (fal coll) fal))))

(defn b [pred v fal]
  (fn [coll] (if (pred coll) v fal)))

(defn arbol-todos [arbol coll]
  (map arbol coll))

