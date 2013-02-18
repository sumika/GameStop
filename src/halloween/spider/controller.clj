(ns halloween.spider.controller
  (:use quil.core)
  (:require [halloween.spider.simulator :as simulator])) ; possibly remove?

(def turn-angle 2)

(def turn-angles [2 3 6])

(defn switch-turn-angle
  [current-turn-angle]
  (let [x current-turn-angle]
  (if (= x 2) 3 (if (= x 3) 6 (if (= x 6) 2)))
    )
  )

(def pen-state false)

(defn toggle-pen
  []
  (def pen-state (not pen-state))
  (if pen-state 
    (simulator/pen :on)
    (simulator/pen :off)
    )
  (if pen-state
    (simulator/add-procedure (list 'pen :on))
    (simulator/add-procedure (list 'pen :off))
    )
  )

; fix me
(defn alt [] (simulator/move) (simulator/add-procedure (list 'move)))
(defn right [] (simulator/turn (* turn-angle -1)) (simulator/add-procedure (list 'turn (* turn-angle -1))))
(defn left [] (simulator/turn turn-angle) (simulator/add-procedure (list 'turn turn-angle)))
(defn up [] (simulator/update-slack 10) (simulator/add-procedure (list 'update-slack 10)))
(defn down [] (simulator/update-slack -10) (simulator/add-procedure (list 'update-slack -10)))
(defn command [] (def turn-angle (switch-turn-angle turn-angle)))
(defn shift [] (toggle-pen))
(defn z [] (binding [*ns* *ns*] (in-ns 'halloween.spider.simulator) (simulator/undo)))

(defn key-press []

 (println (str "Key pressed: " (int (raw-key))))
  
  ;(println key-code))
  ;(println key-code)
  (case (key-as-keyword)
  :alt (alt)
  :right (right)
  :left (left)
  :up (up)
  :down (down)
  :command (command)
  :shift (shift)
  :z (z)
  (println (key-as-keyword)))
  
  
  (defn save-procedure 
    "Saves drawing results to .clj file."
    []
    ;(spit "saved-drawing-test.clj" (with-out-str (clojure.pprint/code-dispatch (clojure.pprint/pprint (simulator/get-procedure)))))
    (spit "saved-drawing-test.clj" (with-out-str (clojure.pprint/pprint (simulator/get-procedure))))
    )
  
  )