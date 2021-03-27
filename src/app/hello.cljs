(ns app.hello
  (:require 
            [app.components.checkbox :as checkbox]
            [app.components.dropdown :as dropdown]
            [app.components.file-uploader :as file-uploader]
            
            [reagent.core :as r]))

(def dropdown-options [{:title "One" :value "One"}
                       {:title "Two" :value "Two"}
                       {:title "Three" :value "Three"}])


(defn hello []
  (let [checkbox-state (r/atom false)]
    (fn [] [:<>
            [file-uploader/file-uploader {:label "Upload file"
                                          :description "Max 500mb file"
                                          :on-change #(js/console.log (-> % .-target .-files (aget 0) .-name))
                                          :button-text "Drag and drop files here or upload"
                                          }]
            [:hr]
            [:p (str @checkbox-state)]
            [checkbox/checkbox {:label "Test Check 1"
                                :on-change #(reset! checkbox-state (-> % .-target .-checked))}]
            [:hr]
            [dropdown/dropdown {:on-select #(js/console.log %)
                                :label "Dropdown text"
                                :error? true
                                :inline? true
                                :error-text "This field is required"
                                :options dropdown-options
                                :dropdown-button-text "Options mike"}]])))
