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

import { selectors, testIds } from '../support/consts';

const paths = {
  hero: 'ComponentOverlay_pagecontainer/level'
};

describe('Hero component', function () {
  beforeEach(() => {
    cy.login();
  });

  it('renders correctly in preview mode', function () {
    cy.visit('/content/bulma-tests/pages/level.html');
    cy.percySnapshotPreview('Level preview');

    cy.contains('Search').click();
    cy.contains('Bulma').should('be.visible');
  });

  it('renders correctly in edit mode', function () {
    cy.intercept(
      'POST',
      '**/pagecontainer/level.websight-dialogs-service.save-properties.action'
    ).as('saveProperties');

    cy.visit(
      '/apps/websight/index.html/content/bulma-tests/pages/level::editor'
    );

    cy.getByTestId(paths.hero)
      .click()
      .find(selectors.overlayName)
      .should('have.text', 'Level container');

    cy.percySnapshotPageEditor('Level editor');

    cy.getByTestId(testIds.editIcon).click();

    cy.percySnapshotDialog('Hero dialog');

    cy.getByTestId(testIds.dialogSubmitButton).click();

    cy.wait('@saveProperties');

    cy.request(
      '/content/bulma-tests/pages/level/jcr:content/pagecontainer/level.json'
    )
      .its('body')
      .should('deep.eq', {
        levelType: 'positioned',
        'sling:resourceType': 'bulma/components/level',
        'jcr:primaryType': 'nt:unstructured'
      });

    cy.request(
        '/content/bulma-tests/pages/level/jcr:content/pagecontainer/level_1_1.json'
    )
    .its('body')
    .should('deep.eq', {
      isVertical: 'false',
      levelType: 'centered',
      'jcr:primaryType': 'nt:unstructured',
      'sling:resourceType': 'bulma/components/level',
    });
  });
});
