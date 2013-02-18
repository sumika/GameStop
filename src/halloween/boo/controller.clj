(ns halloween.boo.controller
  (:use quil.core)
  (:require [halloween.boo.simulator :as simulator])) ; possibly remove?

(def mouse-coords (atom {:x 0 :y 0}))

(def key-pressed :none)

(defn control
  "Returns a set of data that shows which buttons were pressed / mouse position etc."
  []
  mouse-coords
)

(defn mouse-moved
  "Updates mouse position in relationship to the the renderer's window."
  []
  (reset! mouse-coords {:x (mouse-x) :y (mouse-y)})
)