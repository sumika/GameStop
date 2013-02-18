(ns halloween.spider.renderer
  (:use quil.core))

(def last-location)


(defn end [pen]
  (vertex (last-location :x) (last-location :y))
  (end-shape)
  (if pen (stroke 255 100) (stroke 255 50))
  (begin-shape)
  )

(defn render [spider]
  
  (background 0)
  (translate (/ (width) 2) (/ (height) 2))
  
  (let [x   ((spider :location) :x)
        y   ((spider :location) :y)
        orientation   (spider :orientation)
        web (spider :web)
        pen (:pen (meta (peek web)))]
    
  (stroke 255 100)

  (doseq [strand web]
    (if (= (:pen (meta strand)) :on)
      (stroke 255 120)
      (stroke 255 0 0 50)
      )
    
    (push-matrix)
    (def t1 (random -1.0 1.0))
    (def t2 (random -1.0 1.0))
    ;(stroke (random 255) (random 255) (random 255))

    (translate t1 t2)
    (begin-shape)
    (doseq [location strand]
      (curve-vertex (location :x) (location :y))
      )
    (end-shape)
    
    (pop-matrix)
    )
 
  (if (= pen :on)
    (stroke 200)
    (stroke 255 0 0 100)
    )
  (line ((peek (peek web)) :x) ((peek (peek web)) :y) x y)
 
  ;begin drawing spider icon
  (translate x y)
  (rotate (* -1 orientation))
  
  (let [size 20
        quarter-size (* size 1/4)
        three-quarter-size (* size 3/4)]
    
  (stroke 255 0 0)
    
  ;pincers
  (line (+ (* size 3/2)) 0 (+ (* size 3/2) (/ size 5)) (+ (/ size  10)))
  (line (+ (* size 3/2)) 0 (+ (* size 3/2) (/ size 5)) (+ (/ size -10)))

  ;left legs
  (line (+ (/ size 2)) 0 (- quarter-size) (- three-quarter-size))
  (line (+ (/ size 2)) 0 (+ quarter-size) (- three-quarter-size))
  (line (+ (/ size 2)) 0 (+ three-quarter-size) (- three-quarter-size))
  (line (+ (/ size 2)) 0 (+ size quarter-size) (- three-quarter-size))
  
  ;right legs
  (line (+ (/ size 2)) 0 (- quarter-size) (+ three-quarter-size))
  (line (+ (/ size 2)) 0 (+ quarter-size) (+ three-quarter-size))
  (line (+ (/ size 2)) 0 (+ three-quarter-size) (+ three-quarter-size))
  (line (+ (/ size 2)) 0 (+ size quarter-size) (+ three-quarter-size))
  
  ;body
  (fill 80 0 0)
  (ellipse (+ (/ size 2)) 0 size size)
  (ellipse (+ (/ size 2) (* size 3/4) ) 0 (/ size 2) (/ size 2))
  
  ;cutters
  (when (= pen :off)
    (stroke 255 0 0)
    (line 0 0 (* -1 quarter-size) (+ (/ size  8)))
    (line 0 0 (* -1 quarter-size) (+ (/ size -8))))
  )
  ))
