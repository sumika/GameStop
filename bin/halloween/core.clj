(ns halloween.core
  (:gen-class)
  (:use quil.core)
  (:require [halloween.boo.renderer :as boo.renderer])
  (:require [halloween.boo.simulator :as boo.simulator])
  (:require [halloween.boo.controller :as boo.controller])
  
  (:require [halloween.spider.renderer :as spider.renderer])
  (:require [halloween.spider.simulator :as spider.simulator])
  (:require [halloween.spider.controller :as spider.controller])
  
  (:import [processing.core PConstants])
  )

    ;
  ; halloween TODO
    ;
  
  ; set up git repo

(defn boo!
  "Starts 'Halloween!'"
  [& arguments]
  (defsketch halloween-window
  :title "Halloween!"
  :setup   (fn [] (frame-rate 12) (no-smooth) (no-fill) (stroke 255))
  :draw (fn [] (boo.renderer/render (boo.simulator/simulate (boo.controller/control))))
  :mouse-moved boo.controller/mouse-moved
  :size [640 480]))

; (boo!) ; game world
  
    ;
  ; boo TODO
    ;
  
  ; ?
  
(defn spider!
  "Starts the 'Spider!'" 
  [& arguments]
  (defsketch spider-window
  :title "Spider!"
  :setup   (fn [] (hint PConstants/DISABLE_OPENGL_2X_SMOOTH) (frame-rate 60) (no-fill) (stroke 255) (stroke-weight 1))
  :draw (fn [] (spider.renderer/render (spider.simulator/simulate)))
  :key-pressed spider.controller/key-press
  ;:focus-lost spider.controller/save-procedure
  ;:focus-gained (fn[] (binding [*ns* *ns*] (in-ns 'halloween.spider.simulator) (spider.simulator/load-procedure)))
  ;:on-close
  ;:renderer :opengl
  :size [640 480]))

  (spider!) ; graphics editor
  
    ;
  ; spider TODO
    ;
    
  ; DONE basic formatter and input / output
  ; DONE pen up and down
  ; DONE meta data / sort of
  
  ; better i/o  w/file browser of some kind?
  ; unit size / slack (in pixels?) that refers to the (move) argument
  ; eventually add / concatenate code in a smart way with dotimes and stuff - read through the list to find duplicate functions or patterns perhaps
  ; get this to work interactively with basic functions and function calls
  ; add file browser and undo
  ; add something for metadata tagging for animation / interaction etc
  ; think more about the fix me
  
  
  
  
  ; (defn -main [] (boo!)) ; for exporting a java application, the (-main) function must be defined.
  ; (System/exit 0) ; quits application
