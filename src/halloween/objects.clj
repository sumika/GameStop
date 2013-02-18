(ns halloween.objects)

(def objects {
  :house
  [[10 100 30 40]
  [100 100 250 200]
  [10 100 40 500]
  [0 450 40 10]]
  
  :car
  [[10 100 30 40]
  [100 100 250 200]
  [10 100 40 500]]
})

;(spit "saved-objects.clj" objects)

;(def objects (binding [*read-eval* false] (read-string (slurp "saved-objects.clj"))))


; forward - turn30 - closeFromLastPenDown - penup - pendown 
; think about zoom levels?