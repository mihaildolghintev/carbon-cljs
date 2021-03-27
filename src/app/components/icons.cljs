(ns app.components.icons)

(defn icon-plus-button []
  [:svg {:focusable false
         :preserve-aspect-ratio "xMidYMid meet"
         :style {:will-change :transform}
         :xmlns "http://www.w3.org/2000/svg"
         :class ["bx--btn__icon"]
         :width 16
         :height 16
         :view-box "0 0 16 16"
         :aria-hidden true
         }
   [:path {:d "M9 7L9 3 7 3 7 7 3 7 3 9 7 9 7 13 9 13 9 9 13 9 13 7z"}]])


(defn icon-dropdown-arrow []
  [:svg {:focusable false
         :preserve-aspect-ratio "xMidYMid meet"
         :style {:will-change :transform}
         :xmlns "http://www.w3.org/2000/svg"
         :class ["bx--dropdown__arrow"]
         :width 16
         :height 16
         :view-box "0 0 16 16"
         :aria-hidden true
         }
   [:path {:d "M8 11L3 6 3.7 5.3 8 9.6 12.3 5.3 13 6z"}]])


(defn icon-file-complete []
  [:svg {:focusable false
         :preserve-aspect-ratio "xMidYMid meet"
         :style {:will-change :transform}
         :xmlns "http://www.w3.org/2000/svg"
         :class ["bx--file-complete"]
         :width 16
         :height 16
         :view-box "0 0 16 16"
         :aria-hidden true}
   [:path {:d "M8,1C4.1,1,1,4.1,1,8c0,3.9,3.1,7,7,7s7-3.1,7-7C15,4.1,11.9,1,8,1z M7,11L4.3,8.3l0.9-0.8L7,9.3l4-3.9l0.9,0.8L7,11z"}]
   [:path {:d "M7,11L4.3,8.3l0.9-0.8L7,9.3l4-3.9l0.9,0.8L7,11z"}]])

(defn icon-loading-circle []
  [:svg {:class ["bx--loading__svg"]
         :view-box "-75 -75 150 150"}
   [:circle {:class ["bx--loading__background"]
             :cx "0"
             :cy "0"
             :r "26.8125"}]
   [:circle {:class ["bx--loading__stroke"]
             :cx "0"
             :cy "0"
             :r "26.8125"}]])


(defn icon-close-button []
  [:svg {:focusable false
         :preserve-aspect-ratio "xMidYMid meet"
         :style {:will-change :transform}
         :xmlns "http://www.w3.org/2000/svg"
         :width 16
         :height 16
         :view-box "0 0 16 16"
         :aria-hidden true
         }
   [:path {:d "M12 4.7l-.7-.7L8 7.3 4.7 4l-.7.7L7.3 8 4 11.3l.7.7L8 8.7l3.3 3.3.7-.7L8.7 8z"
           :fill "#231F20"}]])
