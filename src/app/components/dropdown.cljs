(ns app.components.dropdown
  (:require
   [app.components.icons :as icons]
   [reagent.core :as r]
   [cljs.spec.alpha :as s]))

(s/def :dropdown/label string?)
(s/def :option/title string?)
(s/def :option/value string?)
(s/def :dropdown/option (s/keys :req-un [:option/title :option/value]))
(s/def :dropdown/options (s/coll-of :dropdown/option))
(s/def ::dropdown (s/keys :req-un [:dropdown/options
                                   :dropdown/label]))

(defn- dropdown-list [& children]
  [:ul {:class ["bx--dropdown-list"]
        :role :menu
        :tab-index 0
        :aria-hidden true
        :whmenuanchor :left}
   (into [:<>] children)])

(defn- dropdown-list-item [{:keys [title value on-select]}]
  [:li {:class ["bx--dropdown-item"]
        :title title}
   [:a {:class ["bx--dropdown-link"]
        :tab-index -1
        :on-click #(on-select value)
        :role "menuitemradio"
        :aria-checked false}
    value]])

(defn dropdown [{:keys [dropdown-button-text options label error? error-text inline?]
                 :as props}]
  {:pre [(s/valid? ::dropdown props)]}
  (r/with-let [dropdown-state (r/atom false)
               dropdown-button-text (r/atom
                                     (if (and dropdown-button-text
                                              (seq options))
                                       dropdown-button-text
                                       (-> options first :title)))
               dropdown-ref (r/atom nil)
               mouse-handler (fn [e]
                               (when
                                (and
                                 ((complement nil?) @dropdown-ref)
                                 (not (-> @dropdown-ref (.contains (-> e .-target)))))
                                 (reset! dropdown-state false)))
               _ (js/document.addEventListener "mousedown" mouse-handler)]
    (fn [{:keys [on-select]}]
      [:div {:class ["bx--dropdown__wrapper"
                     (when inline? "bx--dropdown__wrapper--inline")]}
       [:span {:class ["bx--label"]} label]
       [:div {:class ["bx--dropdown"
                      (when error? "bx--dropdown--invalid")
                      (when @dropdown-state "bx--dropdown--open")]
              :ref #(reset! dropdown-ref %)}
        [:button {:class ["bx--dropdown-text"]
                  :aria-haspopup true
                  :aria-expanded false
                  :on-click #(reset! dropdown-state (not @dropdown-state))
                  :type :button}
         [:span {:class ["bx--dropdown-text__inner"]} @dropdown-button-text]
         [:span {:class ["bx--dropdown__arrow-container"]}
          [icons/icon-dropdown-arrow]]]
        (when @dropdown-state
          [dropdown-list
           (for [dropdown-item options]
             [dropdown-list-item {:key (:value dropdown-item)
                                  :title (:title dropdown-item)
                                  :value (:value dropdown-item)
                                  :on-select #(do
                                                (when on-select (on-select %))
                                                (reset! dropdown-button-text (:title dropdown-item))
                                                (reset! dropdown-state false))}])])]
       (when error? [:div {:class ["bx--form-requirement"]} error-text])])
    (finally js/document.removeEventListener "mousedown" mouse-handler)))
