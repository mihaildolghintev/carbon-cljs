(ns app.components.spinners
  (:require [app.components.icons :refer [icon-loading-circle]]))

(defn spinner-inline []
  [:div {:class ["bx--inline-loading__animation"]}
   [:div {:class ["bx--loading"
                  "bx--loading--small"]}
    [icon-loading-circle]]])
