/*
 * Copyright (C) 2022 Dynamic Solutions
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import React from "/apps/websight-atlaskit-esm/web-resources/react.js";
import { useActionRef } from "/apps/websight-pages-editor-view/web-resources/actions/common.js";
import { performTableRestAction } from "./common.js";

const AddTableRowBeforeAction = React.forwardRef((props, ref) => {
  const { resourcePath } = props;
  useActionRef({
    execute: ({
      editModeStore
    }) => performTableRestAction(editModeStore, resourcePath, "add-table-row", true)
  }, ref);
  return null;
});

const action = {
  data: {
    name: 'Add row before',
    icon: 'keyboard_arrow_up'
  },
  actionComponent: AddTableRowBeforeAction
};
export default action;