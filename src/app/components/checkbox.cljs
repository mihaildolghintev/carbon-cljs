(ns app.components.checkbox
  (:require [nano-id.core :refer [nano-id]]
            [cljs.spec.alpha :as s]))

(s/def :checkbox/label string?)
(s/def ::checkbox (s/keys :req-un [:checkbox/label]))

(defn checkbox [{:keys [checked on-change disabled? label]
                 :as props}]
  {:pre [(s/valid? ::checkbox props)]}
  (let [id (nano-id)]
    [:div.bx--form-item.bx--checkbox-wrapper
     [:input {:id id
              :class ["bx--checkbox"]
              :type :checkbox
              :disabled disabled?
              :checked checked
              :on-change on-change}]
     [:label {:for id
              :class ["bx--checkbox-label"]} label]]))
