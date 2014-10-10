(ns drag.main
  (:require-macros [cljs.core.async.macros :refer (go)])
  (:require
    [reagent.core :as reagent :refer [atom]]
    [cljs-http.client :as http]
    [cljs.core.async :refer (<!)]
    [goog.events :as events])
  (:import [goog.events EventType]))

(enable-console-print!)

(def black-hole-pos {:x 400 :y 400})
(def draggable (atom {:x 100 :y 100 :alive? true}))

(defn close? [x y]
  (and (< (Math/abs (- x (:x black-hole-pos))) 50)
       (< (Math/abs (- y (:y black-hole-pos))) 50)))

(defn get-client-rect [evt]
  (let [r (.getBoundingClientRect (.-target evt))]
    {:left (.-left r), :top (.-top r)}))

(defn draggable-button []
  (let [mouse-move-handler
        (fn [offset]
          (fn [evt]
            (let [x (- (.-clientX evt) (:x offset))
                  y (- (.-clientY evt) (:y offset))]
              (if (close? x y)
                (reset! draggable {:alive? false})
                (reset! draggable {:x x
                                   :y y
                                   :alive? true})))))
        mouse-up-handler
        (fn [on-move]
          (fn me [evt]
            (events/unlisten js/window EventType.MOUSEMOVE
                             on-move)))]
    (fn []
      [:div
       [:h1 (pr-str @draggable)]
       [:button.btn.btn-default
        {:style {:position "absolute"
                 :left (str (:x black-hole-pos) "px");
                 :top (str (:y black-hole-pos) "px")
                 :background "color: black;"}}
        "Not here please"]
       (if (:alive? @draggable)
         [:button.btn.btn-default
          {:style {:position "absolute"
                   :left (str (:x @draggable) "px");
                   :top (str (:y @draggable) "px")}
           :on-mouse-down (fn [e]
                            (let [{:keys [left top]} (get-client-rect e)
                                  offset {:x (- (.-clientX e) left)
                                          :y (- (.-clientY e) top)}
                                  on-move (mouse-move-handler offset)]
                              (events/listen js/window EventType.MOUSEMOVE
                                             on-move)
                              (events/listen js/window EventType.MOUSEUP
                                             (mouse-up-handler on-move)))
                            )}
          "Drag me"])])))

(reagent/render-component [draggable-button]
                          (js/document.getElementById "app"))
