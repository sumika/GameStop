(ns halloween.boo.renderer 
  (:use quil.core))

(defn render [coords]
  (background 100)
  (stroke 255)
  (doseq [c coords] (line (c 0) (c 1) (c 2) (c 3)))
  (stroke 255 0 0)
  (ellipse (/ (width) 2) (/ (height) 2) 10 10)
  )





  