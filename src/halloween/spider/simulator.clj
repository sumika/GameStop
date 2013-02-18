(ns halloween.spider.simulator)

(def orientation 0)
(def scale 1)
(def slack 10)
(def strand (with-meta [{:x 0 :y 0}] {:pen :off}))
(def web [strand])

(def procedure [])

  
(defn update-slack ; possibly make better?
  [amount]
  
  (def slack (+ slack amount))
  
  (if (>= slack 100)
    (def slack 100))
  
  (if (<= slack 10)
    (def slack 10))
  )

(defn get-new-location
  [location new-slack] {
   :x (+ (location :x) (* new-slack (Math/cos orientation)))
   :y (- (location :y) (* new-slack (Math/sin orientation)))
   }
  )

(defn move
  [& amount]
  (if (nil? amount) (def new-slack slack) (def new-slack (first amount)))
  (def strand (conj strand (get-new-location (peek strand) new-slack)))
  )

(defn add-strand-to-web
  [pen-state]
  (def web (conj web strand))
  (def strand (with-meta [(peek strand)] {:pen pen-state}))
  )

(defn pen
  [state]
  (if (> (count strand) 0) 
    (if-not (= (:pen (meta strand)) state)
      (add-strand-to-web state)
    ))
)

(defn turn ; default turns right 90 degrees 
  [& angle]
  (if (nil? angle) (def new-angle -2) (def new-angle (first angle)))
  (def orientation (+ orientation (/ Math/PI new-angle)))
  )

(defn get-procedure
  "Returns & generates the drawing instructions for this drawing."
  []
  procedure)

(defn add-procedure [p] (def procedure (conj procedure p)))

(defn reset-procedure
  []
  (def strand [{:x 0 :y 0}])
  (def web [strand])
  (def slack 10)
  (def orientation 0)
  )

  (defn execute-procedure
   "Executes a set of spider commands."
   [p]
   (doseq [x procedure] (eval x))
   )
  
  (defn undo
    []
    (when (seq procedure)
      (def procedure (pop procedure))
      (reset-procedure)
      (execute-procedure procedure)
      )
    )

  (defn load-procedure
   "Loads .clj file as a list."
   []
   (execute-procedure (binding [*read-eval* false] (read-string (slurp "saved-drawing-test.clj"))))
   )

(defn simulate
  "Simulates one step and returns display coordinates."
  []
  {:location (get-new-location (peek strand) slack)
   :orientation orientation
   :web (conj web strand)
   }
)

(comment ; for scaling the web
  ;(vec (for [location strand]
               ;(with-meta {:x (* (location :x) scale) :y (* (location :y) scale)} (meta location)) ; sends a scaled strand
               ;))
)