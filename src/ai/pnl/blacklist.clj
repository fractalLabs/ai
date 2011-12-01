(ns ai.pnl.blacklist)


(def *black-list* (str (System/getProperty "user.home") "/black-list.txt"))


(defn re-pattern-id 
  "crea el re-pattern para encontrar donde comienza la blacklist de id"
  [id]
  (re-pattern (str "\\*\\*\\*\\*\\*" id "\\*\\*\\*\\*\\*")))

(def end-list-pattern #"\*\*\*\*\*[\w\W]+\*\*\*\*\*")

(defn black-list-from
  "La lista negra identificada por id"
  [id]
  (let [content (re-seq #"[^\n]+" (slurp *black-list*))]
    (take-while
      (complement #(re-find end-list-pattern %)) 
      (rest 
        (drop-while 
          (complement #(re-find (re-pattern-id id) %))
          content)))))
