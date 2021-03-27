(ns app.components.buttons
  (:require [cljs.spec.alpha :as s]
            [app.components.icons :refer [icon-close-button]]))


(s/def :button/variant #{:primary :secondary :tertiary :danger :ghost})
(s/def :button/size #{:small :field})

(defn- button-size [size]
  {:pre [(s/valid? :button/size size)]}
  (size {:small "bx--btn--sm"
         :field "bx--btn--field"
         }))

(-> (s/explain-data :button/variant :fa)
    :cljs.spec.alpha/problems
    (first)
    :via
    )


(defn- button-variant [variant]
  {:pre [(s/valid? :button/variant variant)]}
  (variant {:primary "bx--btn--primary"
            :secondary "bx--btn--secondary"
            :tertiary "bx--btn--tertiary"
            :danger "bx--btn--danger"
            :ghost "bx--btn--ghost"}))



(defn button-set [& children]
  [:div.bx--btn-set
   (into [:<>] children)])

(defn button [{:keys [icon
                      icon-only?
                      disabled?
                      size
                      variant
                      on-click]
               :or {variant :primary}} & children]
  [:button {:class ["bx--btn"
                    (button-variant variant)
                    (when size (button-size size))
                    (when icon-only? "bx--btn--icon-only")]
            :type :button
            :disabled disabled?
            :on-click on-click}
   (when-not icon-only?
     (into [:<>] children))
   (when icon [icon])])


(defn button-close [{:keys [on-click]}]
  [:button {:class ["bx--file-close"]
            :type :button
            :aria-label "close"
            :on-click on-click}
   [icon-close-button]])
