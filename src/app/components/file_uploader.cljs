(ns app.components.file-uploader
  (:require [nano-id.core :refer [nano-id]]
            [cljs.spec.alpha :as s]
            [reagent.core :as r]
            [app.components.spinners :refer [spinner-inline]]
            [app.components.icons :refer [icon-file-complete]]
            [app.components.buttons :refer [button-close]]))

(s/def :file-uploader/label string?)
(s/def :file-uploader/description string?)
(s/def :file-uploader/button-text string?)


(s/def ::file-uploader
  (s/keys :req-un [:file-uploader/label
                   :file-uploader/description
                   :file-uploader/button-text]))


(defn- file-container [{:keys [file-name clear-file-handler status]}]
  [:div {:class ["bx--file-container"]}
   [:span {:class ["bx--file__selected-file"]}
    [:p {:class ["bx--file-filename"]} @file-name]
    [:span {:class ["bx--file__state-container"]}
     (condp = status
       :loading [spinner-inline]
       :done [icon-file-complete]
       [button-close {:on-click clear-file-handler}])]]])

(defn file-uploader [{:keys [label description button-text on-change on-file-clear]
                      :as props}]
  {:pre [(s/valid? ::file-uploader props)]}
  (let [id (nano-id)
        file-name (r/atom nil)
        change-file-handler #(do
                               (when on-change (on-change %))
                               (reset! file-name (-> % .-target .-files (aget 0) .-name)))
        clear-file-handler #(do
                              (when on-file-clear on-file-clear)
                              (reset! file-name nil))]
    (fn []
      [:div {:class ["bx--form-item"]}
       [:strong {:class ["bx--file--label"]} label]
       [:p {:class ["bx--label-description"]} description]
       [:div {:class ["bx--file"]}
        [:label {:for id
                 :class ["bx--file-browse-btn"]
                 :role :button
                 :tab-index 0}
         [:div {:class ["bx--file__drop-container"]} button-text
          [:input {:type :file
                   :class ["bx--file-input"]
                   :id id
                   :on-change change-file-handler
                   :multiple true}]]]
        (when @file-name
          [file-container {:file-name file-name
                           :status :done
                           :clear-file-handler clear-file-handler}])]])))
