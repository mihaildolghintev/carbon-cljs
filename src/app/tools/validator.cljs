(ns app.tools.validator 
  (:require
    [cljs.spec.alpha :as s]))

(defn spec-problem? [spec value]
  (-> (s/explain-data spec value)
      :cljs.spec.alpha/problems))

(defn spec-validate [spec value]
  (->> (spec-problem? spec value)
       (first)
       :via))

(defn get-message [via messages]
  (->> (reverse via)
       (some messages)
       (js/console.error )))

